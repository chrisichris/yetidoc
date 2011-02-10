// ex: se sts=4 sw=4 expandtab:

/*
 * Yeti language compiler java bytecode generator.
 *
 * Copyright (c) 2007,2008 Madis Janson
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.yetidoc;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import yeti.lang.compiler.CompileException;

public class YetiDocTask extends Task {
    private java.io.File srcDirF;
    private java.io.File destDirF;
    private Path classPath;
    private String frameworkName = "";

    public void setSrcDir(String dir) {
        this.srcDirF = new java.io.File(dir);
    }

    public void setDestDir(String dir) {
        destDirF = new java.io.File(dir);
    }

    public void setFrameworkName(String fn){
        this.frameworkName = fn == null ? "" : fn.trim();
    }


    public Path createClasspath() {
        if (classPath == null) {
            classPath = new Path(getProject());
        }
        return classPath;
    }

    public void execute() {
        if (srcDirF == null)
            throw new BuildException("srcDir is not set");
        if (destDirF == null)
            throw new BuildException("destDir is missing");

        String[] classPathA =
            this.classPath == null ? new String[0] : this.classPath.list();
        this.frameworkName = this.frameworkName == null ? "" : frameworkName;

        String srcDir = srcDirF.getPath();
        String destDir = destDirF.getPath();

        log("Writing yeti docs .");
        try {
            YetiDocJavaApi.writeDocForDir(classPathA, srcDir, destDir, this.frameworkName);
        } catch (CompileException ex) {
            throw new BuildException(ex.getMessage());
        } catch (BuildException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            throw new BuildException(ex);
        }
    }
}
