
<jsp:include page="header.jsp">
  <jsp:param name="title" value="JSword - Config Subsystem" />
</jsp:include>

<h1>The Config Sub-System</h1>

<h2>Introduction</h2>

<p>Config is (mostly) all kept in a few packages in the util source tree. 
  The design aims for the following goals:</p>
<ul>
  <li>Application Transparency - It should be possible to add a configuration 
	dialog to an application without adding hundreds of hooks either to 
	your application to read the current state, or to the configuration 
	system to work with the application. This is achieved via an xml config 
	file and a healthy dose of reflection.</li>
  <li>View Independance - Currently there are a number of Swing front 
	ends - a Mozilla style config dialog with a tree, a more conventional 
	tabbed dialog, and a prototype wizard style interface. There has also 
	been a servlet front-end however the code to do this has suffered 
	bit-rot, and should not be considered useful. It does however prove 
	the view independance concept.</li>
</ul>

<h3>How To Use Config</h3>
<p>There are a number of simple steps. First a config.xml file is needed 
  to tell the config system what to configure and how.</p>
<pre>
&lt;config>

  <font color="#00CC00">&lt;!-- A configuration is a set of options ... --></font>
  <font color="#00CC00">&lt;!-- The key is a dot separated name - Imaging this in a Mozilla tree or some nested tabs. --></font>
  &lt;option key="Bibles.Sword.Base Directory" type="string">
    <font color="#00CC00">&lt;!-- The type (above) along with the introspect line configures what JavaBean methods will be called --></font>
    &lt;introspect class="org.crosswire.jsword.book.sword.SwordBibleDriver" property="SwordDir"/>
    <font color="#00CC00">&lt;!-- The tool-tip (or similar) describing what is going on --></font>
    &lt;help>Where is the SWORD Project base directory.&lt;/help>
  &lt;/option>

  <font color="#00CC00">&lt;!-- Another option, this time it is a boolean option which will show up as a tickbox --></font>
  &lt;option key="Bibles.Display.Persistent Naming" level="advanced" type="boolean">
    &lt;introspect class="org.crosswire.jsword.passage.PassageUtil" property="PersistentNaming"/>
    &lt;help>True if the passage editor re-writes the references to conform to its notation.&lt;/help>
  &lt;/option>

  <font color="#00CC00">&lt;!-- Another type again this one for the look and feel. -->
  &lt;!-- The reason for the helper class here is to alter windows that are not currently mapped -->
</font>  &lt;option key="Looks.Look and Feel" type="class">
    &lt;introspect class="org.crosswire.common.swing.LookAndFeelUtil" property="LookAndFeel"/>
    &lt;help>The look and feel of the application&lt;/help>
  &lt;/option>

  <font color="#00CC00">&lt;!-- When we have have an Enum style config option ... -->
</font>  &lt;option key="Bibles.Display.Book Case" level="advanced" type="int-options">
    &lt;introspect class="org.crosswire.jsword.passage.Books" property="Case"/>
    &lt;help>What case should we use to display the references.&lt;/help>
    &lt;alternative number="0" name="lower"/>
    &lt;alternative number="1" name="Sentance"/>
    &lt;alternative number="2" name="UPPER"/>
    &lt;alternative number="3" name="mIXeD"/>
  &lt;/option>

  <font color="#00CC00">&lt;!-- The options here are more complex and need to be provided as a string array by Java code (see below) -->
</font>  &lt;option key="Bibles.Default" type="string-options">
    &lt;introspect class="org.crosswire.jsword.book.Bibles" property="DefaultName"/>
    &lt;help>Which of the available Bibles is the default.&lt;/help>
    &lt;map name="biblenames"/>
  &lt;/option>

  <font color="#00CC00">&lt;!-- This option is 'advanced' which means it is not visible to all users (see below) -->
</font>  &lt;option key="Advanced.Source Path" level="advanced" type="path">
    &lt;introspect class="org.crosswire.common.swing.DetailedExceptionPane" property="SourcePath"/>
    &lt;help>The directories to search for source code in when investigating an exception.&lt;/help>
  &lt;/option>

  <font color="#00CC00">&lt;!-- When the choice is very custom you can always do your own implementation -->
  &lt;!-- This allows us to set users levels so not everyone gets asked hard questions -->
</font>  &lt;option key="Advanced.User Level" type="custom" class="org.crosswire.common.util.UserLevel$UserLevelChoice">
    &lt;help>How advanced is your knowledge of this program.&lt;/help>
  &lt;/option>

  <font color="#00CC00">&lt;!-- There are other examples in config.xml -->
</font>
&lt;/config>

</pre>

<p>Then you need to add the Java code:
<pre><font color="#00CC00">
// To load the config.xml file:
</font>Config config = new Config("Tool Shed Options");
Document xmlconfig = Project.resource().getDocument("config"); <font color="#00CC00">// Or whatever to get a JDOM Document</font>
config.add(xmlconfig);

<font color="#00CC00">// To load a saved config</font>
config.setProperties(Project.resource().getProperties("desktop")); <font color="#00CC00">// Or however you get a Properties</font>
config.localToApplication(true);

<font color="#00CC00">// And display it ...</font>
URL configurl = Project.resource().getPropertiesURL("desktop"); <font color="#00CC00">// URL of the Properties file to save to</font>
SwingConfig.showDialog(config, parentWind, configurl);

<font color="#00CC00">// The code above needed help in setting up a string choice. This is how ...</font>
ChoiceFactory.getDataMap().put("biblenames", Bibles.getBibleNames());
</pre>

<p>There are more examples in <code>org.crosswire.jsword.view.swing.desktop.OptionsAction.</code></p>

<jsp:include page="footer.jsp" />
