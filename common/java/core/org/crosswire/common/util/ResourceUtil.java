package org.crosswire.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * Better implemenetations of the getResource methods with less ambiguity and
 * that are less dependent on the specific classloader situation.
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
 * @author DM Smith [ dmsmith555 at yahoo dot com ]
 * @version $Id$
 */
public class ResourceUtil
{
    /**
     * Prevent Instansiation
     */
    private ResourceUtil()
    {
    }

    /**
     * Generic resource URL fetcher. One way or the other we'll find it!
     * Either as a relative or an absolute reference.
     * @param search The name of the resource (without a leading /) to find
     * @return The requested resource
     * @throws MissingResourceException if the resource can not be found
     */
    public static URL getResource(String search) throws MissingResourceException
    {
        return getResource(CallContext.instance().getCallingClass(), search);
    }

    /**
     * Generic resource URL fetcher. One way or the other we'll find it!
     * Either as a relative or an absolute reference.
     * @param clazz The resource to find
     * @return The requested resource
     * @throws MissingResourceException if the resource can not be found
     */
    public static URL getResource(Class clazz, String resourceName) throws MissingResourceException
    {
        URL resource = new CWClassLoader(clazz).findResource(resourceName);

        if (resource == null)
        {
            throw new MissingResourceException(Msg.NO_RESOURCE.toString(clazz.getName()), clazz.getName(), resourceName);
        }

        return resource;
    }

    /**
     * Generic resource URL fetcher
     * @return The requested resource
     * @throws IOException if there is a problem reading the file
     * @throws MissingResourceException if the resource can not be found
     */
    public static InputStream getResourceAsStream(String search) throws IOException, MissingResourceException
    {
        return getResourceAsStream(CallContext.instance().getCallingClass(), search);
    }

    /**
     * Generic resource URL fetcher
     * @return The requested resource
     * @throws IOException if there is a problem reading the file
     * @throws MissingResourceException if the resource can not be found
     */
    public static InputStream getResourceAsStream(Class clazz, String search) throws IOException, MissingResourceException
    {
        URL url = ResourceUtil.getResource(clazz, search);
        return url.openStream();
    }

    /**
     * Get and load a properties file from the writable area or if that
     * fails from the classpath (where a default ought to be stored)
     * @param subject The name of the desired resource (without any extension)
     * @return The found and loaded properties file
     * @throws IOException if the resource can not be loaded
     * @throws MissingResourceException if the resource can not be found
     */
    public static Properties getProperties(String subject) throws IOException, MissingResourceException
    {
        return getProperties(CallContext.instance().getCallingClass(), subject);
    }

    /**
     * Get and load a properties file from the writable area or if that
     * fails from the classpath (where a default ought to be stored)
     * @param clazz The name of the desired resource
     * @return The found and loaded properties file
     * @throws IOException if the resource can not be loaded
     * @throws MissingResourceException if the resource can not be found
     */
    public static Properties getProperties(Class clazz) throws IOException, MissingResourceException
    {
        return getProperties(clazz, ClassUtil.getShortClassName(clazz));
    }

    /**
     * Get and load a properties file from the writable area or if that
     * fails from the classpath (where a default ought to be stored)
     * @param clazz The name of the desired resource
     * @return The found and loaded properties file
     * @throws IOException if the resource can not be loaded
     * @throws MissingResourceException if the resource can not be found
     */
    private static Properties getProperties(Class clazz, String subject) throws IOException, MissingResourceException
    {
        String lookup = subject + FileUtil.EXTENSION_PROPERTIES;
        InputStream in = getResourceAsStream(clazz, lookup);

        Properties prop = new Properties();
        prop.load(in);

        return prop;
    }
}
