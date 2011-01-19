/*
 * Copyright (c) 2010,2011 --chris--
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

import yeti.lang.Fun;
import yeti.lang.MList;
import yeti.lang.Struct;


/*
 * writeDocForDir classpathA srcDir targetDir frameworkName is array<string> -> string -> string -> string -> string=
 *
 */
public class YetiDocJavaApi {
    private static Struct getStruct() {
        try{
            Class cl = YetiDocJavaApi.class.getClassLoader().loadClass("org.yetidoc.yetidocApi");
            return (Struct) cl.getMethod("eval").invoke(null);
        }catch(Exception ex) {
            throw new IllegalStateException("Please report this is a bug:",ex);
        }
        
    }
    public static void writeDocForDir(String[] classpath, String srcDir, String targetDir, String frameworkName) {
        if(classpath == null) classpath = new String[]{};
        Struct st = getStruct();
        //PArray classpathA = new PArray(classpath,0,classpath.length);
        Fun f = (Fun) st.get("writeDocForDir");
        Object ret = ((Fun)((Fun)((Fun) f.apply(new MList(classpath))).apply(srcDir)).apply(targetDir)).apply(frameworkName);
    }
}
