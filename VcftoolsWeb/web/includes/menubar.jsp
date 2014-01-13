<%-- 
    Document   : menubar
    Created on : Jan 9, 2014, 3:39:43 PM
    Author     : mhroelfes
--%>

<div id="menu">
    <div class="pure-menu pure-menu-open">
        <a class="pure-menu-heading" href="http://www.vcftools.sourceforge.net">VCF</a>

        <ul>
            <li>
                <h3>Login</h3>
                <form method="post" action="login.do">
                    <input id="login" type="text" name="username" />
                    <input id="login" type="password" name="password" />
                    <input id="submit"type="submit" name="userinput" value="Login"/>

                </form>
                <br />
            </li>

            <li class="pure-menu-selected pure-menu-divided "><a href="index.html">Home</a></li>
            <li><a href="about.html">About Vcf</a></li>
            <li><a href="documentation.html">Documentation</a></li>
            <li><a href="program.html">Program</a></li>


            <li class="menu-item-divided ">
                <a href="download.html">Download</a>
            </li>
            <li><a href="http://purecss.io/extend/">Extend</a></li>
            <li><a href="http://blog.purecss.io/">Blog</a></li>
            <li><a href="releases.html">Releases</a></li>

            <li class="menu-item-divided">
                <a href="contact.html">Contact</a>
            </li>
            <li><a href="http://yuilibrary.com/">YUI Library</a></li>
        </ul>
    </div>
</div>
