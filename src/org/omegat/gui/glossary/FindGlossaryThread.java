/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               2008 Alex Buloichik
               2009 Wildrich Fourie
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 **************************************************************************/

package org.omegat.gui.glossary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.omegat.core.Core;
import org.omegat.core.data.StringEntry;
import org.omegat.core.matching.ITokenizer;
import org.omegat.core.matching.Tokenizer;
import org.omegat.util.Token;

/**
 * Class for find glossary entries for current entry in editor.
 *
 * This process looks up the source string entries, and find matched glossary
 * entries.
 * <p>
 * Test cases wheter a glossary entry matches a string entry text:
 * <ul>
 * <li>"Edit" vs "Editing" - doesn't match
 * <li>"Old Line" vs "Hold Line" - doesn't match
 * <li>"Some Text" vs "There was some text there" - OK!
 * <li>"Edit" vs "Editing the edit" - matches OK!
 * <li>"Edit" vs "Edit" - matches OK!
 * </ul>
 *
 * @author Keith Godfrey
 * @author Maxym Mykhalchuk
 * @author Alex Buloichik (alex73mail@gmail.com)
 * @author Wildrich Fourie
 */
public class FindGlossaryThread extends Thread {
    private final GlossaryTextArea glossaryController;

    /**
     * Entry which processed currently.
     *
     * If entry in controller was changed, it means user was moved to other
     * entry, and there is no sense to continue.
     */
    private final StringEntry processedEntry;

    private List<GlossaryEntry> result = new ArrayList<GlossaryEntry>();

    public FindGlossaryThread(final GlossaryTextArea glossaryController,
            final StringEntry entry) {
        this.glossaryController = glossaryController;
        this.processedEntry = entry;
    }

    @Override
    public void run() {
        // computer source entry tokens
        Token[] strTokens = Core.getTokenizer().tokenizeWords(
                processedEntry.getSrcText(), ITokenizer.StemmingMode.GLOSSARY);

        List<GlossaryEntry> entries = glossaryController.manager.getGlossaryEntries();
        if (entries != null) {
            for (GlossaryEntry glosEntry : entries) {
                if (glossaryController.processedEntry != processedEntry) {
                    // Processed entry changed, because user moved to other
                    // entry.
                    // I.e. we don't need to find and display data for old
                    // entry.
                    return;
                }

                // computer glossary entry tokens
                String glosStr = glosEntry.getSrcText();
                Token[] glosTokens = Core.getTokenizer().tokenizeWords(glosStr,
                        ITokenizer.StemmingMode.GLOSSARY);
                int glosTokensN = glosTokens.length;
                if (glosTokensN == 0)
                    continue;

                if (Tokenizer.isContainsAll(strTokens, glosTokens)) {
                    result.add(glosEntry);
                }
            }
        }

        // After the matched entries have been tokenized and listed.
        // We remove the duplicates and combine the synonyms.
        // Then the matches are ordered to display the biggest matches first.
        result = FilterGlossary(result);
        for(int z=0; z < result.size(); z++)
            for(int x=z+1; x < result.size()-1; x++)
            {
                GlossaryEntry zEntry = (GlossaryEntry)result.get(z);
                GlossaryEntry xEntry = (GlossaryEntry)result.get(x);

                if(xEntry.getSrcText().length() > zEntry.getSrcText().length())
                {
                    Object temp = result.get(x);
                    result.set(x, result.get(z));
                    result.set(z,(GlossaryEntry) temp);
                }
            }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (glossaryController.processedEntry == processedEntry) {
                    glossaryController.setGlossaryEntries(result);
                }
            }
        });
    }

    private List FilterGlossary(List<GlossaryEntry> result)
    {
        // First check that entries exist in the list.
        if(result.size() == 0)
            return result;

        List<GlossaryEntry> returnList = new LinkedList<GlossaryEntry>();
        
        // The default replace entry
        GlossaryEntry replaceEntry = new GlossaryEntry("", "", "");        

        //... Remove the duplicates from the list ..............................
        boolean removedDuplicate = false;
        for(int i=0; i < result.size(); i++)
        {
            GlossaryEntry nowEntry = result.get(i);

            if(nowEntry.getSrcText().equals(""))
                continue;

            for(int j=i+1; j < result.size(); j++)
            {
                GlossaryEntry thenEntry = result.get(j);

                if(thenEntry.getSrcText().equals(""))
                    continue;

                // If the Entries are exactely the same, insert a blank entry.
                if(nowEntry.getSrcText().equals(thenEntry.getSrcText()))
                    if(nowEntry.getLocText().equals(thenEntry.getLocText()))
                        if(nowEntry.getCommentText().
                                  equals(thenEntry.getCommentText()))
                        {
                            result.set(j, replaceEntry);
                            removedDuplicate = true;
                        }
            }
        }
        //......................................................................

        //-- Remove the blank entries from the list ----------------------------
        if(removedDuplicate)
        {
            Iterator<GlossaryEntry> myIter = result.iterator();
            List<GlossaryEntry> newList = new LinkedList<GlossaryEntry>();


            while(myIter.hasNext())
            {
                GlossaryEntry checkEntry = myIter.next();
                if(checkEntry.getSrcText().equals("") ||
                   checkEntry.getLocText().equals(""))
                    myIter.remove();
                else
                    newList.add(checkEntry);
            }

            result = newList;
        }
        //----------------------------------------------------------------------

        //~~ Group items with same scrTxt ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        for(int i=0; i < result.size(); i++)
        {
            List<GlossaryEntry> srcList = new LinkedList<GlossaryEntry>();
            GlossaryEntry nowEntry = result.get(i);

            if(nowEntry.getSrcText().equals(""))
                continue;

            srcList.add(nowEntry);

            for(int j=i+1; j < result.size(); j++)
            {
                GlossaryEntry thenEntry = result.get(j);

                // Double check, needed?
                if(thenEntry.getSrcText().equals(""))
                    continue;

                if(nowEntry.getSrcText().equals(thenEntry.getSrcText()))
                {
                    srcList.add(thenEntry);
                    result.set(j, replaceEntry);
                }
            }

            //ии Sort items with same locTxt иииииииииииииииииииииииииииииииииии
            List<GlossaryEntry> sortList = new LinkedList<GlossaryEntry>();
            if(srcList.size() > 1)
            {                
                for(int k=0; k < srcList.size(); k++)
                {
                    GlossaryEntry srcNow = srcList.get(k);

                    if(srcNow.getSrcText().equals(""))
                        continue;

                    sortList.add(srcNow);

                    for(int l=k+1; l < srcList.size(); l++)
                    {
                        GlossaryEntry srcThen = srcList.get(l);

                        if(srcThen.getSrcText().equals(""))
                            continue;

                        if(srcNow.getLocText().equals(srcThen.getLocText()))
                        {
                            sortList.add(srcThen);
                            srcList.set(l, replaceEntry);
                        }
                    }
                }
            }
            else
            {
                sortList = srcList;
            }
            //ииииииииииииииииииииииииииииииииииииииииииииииииииииииииииииииииии


            //== Now put the sortedList together ===============================
            String srcTxt = sortList.get(0).getSrcText();
            String locTxt = sortList.get(0).getLocText();
            String comTxt = "";

            int comCounter = 1;

            String prevLocTxt = sortList.get(0).getLocText();
            String prevComTxt = sortList.get(0).getCommentText();

            if(!prevComTxt.equals(""))
                comTxt = comCounter + ". " + prevComTxt;

            for(int m=1; m < sortList.size(); m++)
            {
                if(!sortList.get(m).getLocText().equals(prevLocTxt))
                {
                    comCounter++;
                    prevLocTxt = sortList.get(m).getLocText();
                    locTxt += ", " + prevLocTxt;
                    // The Comments cannot be equal because all the duplicates 
                    // have been removed earlier.
                    if(!sortList.get(m).getCommentText().equals(""))
                    {
                        if(comTxt.equals(""))
                            comTxt = comCounter + ". "
                                    + sortList.get(m).getCommentText();
                        else
                            comTxt += "\n" + comCounter + ". "
                                    + sortList.get(m).getCommentText();
                    }
                }
                else
                {                    
                    if(!sortList.get(m).getCommentText().equals(""))
                    {
                        if(comTxt.equals(""))
                            comTxt = comCounter + ". "
                                    + sortList.get(m).getCommentText();
                        else
                            comTxt += "\n" + comCounter + ". "
                                    + sortList.get(m).getCommentText();
                    }
                }                
            }
            GlossaryEntry combineEntry = new GlossaryEntry(srcTxt, locTxt, comTxt);
            returnList.add(combineEntry);
            //==================================================================
        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        return returnList;
    }
}
