package org.crosswire.jsword.book.sword;

import org.crosswire.common.util.MsgBase;

/**
 * Compile safe Msg resource settings.
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
 * @see gnu.gpl.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
class Msg extends MsgBase
{
    static final Msg NO_KEY = new Msg("NO_KEY"); //$NON-NLS-1$
    static final Msg BAD_KEY = new Msg("BAD_KEY"); //$NON-NLS-1$
    static final Msg GZIP_FORMAT = new Msg("GZIP_FORMAT"); //$NON-NLS-1$
    static final Msg FILTER_FAIL = new Msg("FILTER_FAIL"); //$NON-NLS-1$
    static final Msg FILE_ONLY = new Msg("FILE_ONLY"); //$NON-NLS-1$
    static final Msg MISSING_FILE = new Msg("MISSING_FILE"); //$NON-NLS-1$
    static final Msg NOT_FOUND = new Msg("NOT_FOUND"); //$NON-NLS-1$
    static final Msg READ_FAIL = new Msg("READ_FAIL"); //$NON-NLS-1$
    static final Msg READ_ONLY = new Msg("READ_ONLY"); //$NON-NLS-1$
    static final Msg COMPRESSION_UNSUPPORTED = new Msg("COMPRESSION_UNSUPPORTED"); //$NON-NLS-1$
    static final Msg TYPE_UNSUPPORTED = new Msg("TYPE_UNSUPPORTED"); //$NON-NLS-1$
    static final Msg TYPE_UNKNOWN = new Msg("TYPE_UNKNOWN"); //$NON-NLS-1$
    static final Msg MISSING_SEARCHER = new Msg("MISSING_SEARCHER"); //$NON-NLS-1$
    static final Msg MISSING_BACKEND = new Msg("MISSING_BACKEND"); //$NON-NLS-1$
    static final Msg DRIVER_READONLY = new Msg("DRIVER_READONLY"); //$NON-NLS-1$
    static final Msg MISSING_NAME = new Msg("MISSING_NAME"); //$NON-NLS-1$
    static final Msg UNDEFINED_MODULE = new Msg("UNDEFINED_MODULE"); //$NON-NLS-1$
    static final Msg UNDEFINED_DATATYPE = new Msg("UNDEFINED_DATATYPE"); //$NON-NLS-1$

    /**
     * Passthrough ctor
     */
    private Msg(String name)
    {
        super(name);
    }
}
