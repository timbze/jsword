
package org.crosswire.jsword.book.search.ser;

import java.util.Iterator;
import java.util.Vector;

import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.passage.Passage;

/**
 * The Search Word for a Word to search for. The default if no other SearchWords
 * match.
 * 
 * <p><table border='1' cellPadding='3' cellSpacing='0'>
 * <tr><td bgColor='white' class='TableRowColor'><font size='-7'>
 *
 * Distribution Licence:<br />
 * JSword is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public License,
 * version 2 as published by the Free Software Foundation.<br />
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br />
 * The License is available on the internet
 * <a href='http://www.gnu.org/copyleft/gpl.html'>here</a>, or by writing to:
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA 02111-1307, USA<br />
 * The copyright to this program is held by it's authors.
 * </font></td></tr></table>
 * @see docs.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
public class SubLeftParamWord implements ParamWord
{
    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.search.ser.ParamWord#getWord(org.crosswire.jsword.book.search.ser.Parser)
     */
    public String getWord(Parser engine) throws BookException
    {
        throw new BookException(Msg.LEFT_PARAM);
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.search.ser.ParamWord#getPassage(org.crosswire.jsword.book.search.ser.Parser)
     */
    public Passage getPassage(Parser engine) throws BookException
    {
        Iterator it = engine.iterator();
        Vector output = new Vector();

        int paren_level = 1;
        while (true)
        {
            if (!engine.iterator().hasNext())
            {
                throw new BookException(Msg.LEFT_BRACKETS);
            }

            SearchWord word = (SearchWord) it.next();

            if (word instanceof SubLeftParamWord)   paren_level++;
            if (word instanceof SubRightParamWord)  paren_level--;

            if (paren_level == 0) break;

            output.addElement(word);
        }

        Parser sub_engine = new Parser(engine.getSearcher(), engine.getSearchMap());
        Passage sub_ref = sub_engine.search(output);

        return sub_ref;
    }
}
