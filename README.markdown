YetiDoc
========

* note this project is not maintained anymore use the build in doc facility of yeti*

[Yeti](http://mth.github.com/yeti/) is ML style functional programming 
language, that runs on the JVM.

Currently yeti only provides simple text-output to the console for api-documentation.

Until Yeti itself can generate html. YetiDoc is a first attemp for an html-api-documentation-generator similar to 
java-doc for Yeti *until* Yeti will provide its own.

Basicly YetiDoc is a html-beautifier around the documentation-features yeti already provides. 

Version
--------
YetiDoc is currently in alpha and definately has still bugs.

Features
--------
- given a source directory generates html-module-descriptions for each yeti 
  source file 
- yeti-api, java-api, ant-task

Limitations
-----------
- no index-file gerneration yet
- YetiDoc can not read the modules package and name form the source-input. 
  Therefore it generates the module name based on the location of the yeti source file. That means that the 
  sourcefile of a module must be in the right directory for its package and the name of the source-file
  must be the simple-name of the module - like with java (public) classes.
  Ie the file for 'module org.foo.themodule' must have the path '[srcDir]/org/foo/themodule.yeti'.
- only for modules which return structs detailed documentation is generated for other modules only the type is reported.  
- very little customization possibilities

Example:
---------
[yeti.lang.std](http://chrisichris.github.com/yetidoc/dist/yetidoc/yeti$lang$std.html)

Installation
-------------
Download the [yetidoc.jar](http://chrisichris.github.com/yetidoc/dist/yetidoc.jar) and put it together with the 
latest [yeti.jar](http://mth.github.com/yeti/) on your classpath.

Ant-Usage
---------
- Define the yetidoc ant-task (org.yetidoc.YetiDocTask)
- at the ant-task specify mandatory the srcdir-attribute (= where the *.yeti files are) 
  and the destdir (= where the html pages will be written)
- optionally specify a frameworkname (= your project name) and the classpath of your project 

    <path id="yeti.classpath">
		<fileset dir="directory where yeti.jar and yeti.doc is">
			<include name="*.jar"/>
	    </fileset>
	</path>
	<target name="yetidoc">
		<taskdef name="yetidoc" classname="org.yetidoc.YetiDocTask">
			<classpath refid="yeti.classpath" />
		</taskdef>
		<yetidoc srcdir="${testdocsrc-dir}" destdir="${testdocs-dir}/anttask"
					frameworkname="the name of your project">
			<classpath refid="project.classpath" />
		</yetidoc>
	</target>	
 
Java-Usage
----------
Use the method on class `org.yetidoc.YetiDocJavaApi` [api-doc](http://chrisichris.github.com/yetidoc/dist/javadoc/org/yetidoc/YetiDocJavaApi.html)

Yeti-Usage
----------
Use the `module org.yetidoc.yetidocapi` see [yeti-api-doc](http://chrisichris.github.com/yetidoc/dist/yetidoc/org$yetidoc$yetidocApi.html#field_writeDocForDir)
