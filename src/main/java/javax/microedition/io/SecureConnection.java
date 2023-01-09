/*
 * @(#)SecureConnection.java	1.13 02/07/24 @(#)
 *
 * Copyright (c) 2001-2002 Sun Microsystems, Inc.  All rights reserved.
 * PROPRIETARY/CONFIDENTIAL
 * Use is subject to license terms.
 */

package javax.microedition.io;

import java.lang.String;
import java.lang.IllegalArgumentException;
import java.io.IOException;
import javax.microedition.pki.CertificateException;

/**
 * This interface defines the secure socket stream connection.
 * A secure connection is established using
 * <CODE>Connector.open</CODE> with the scheme "ssl" and the secure
 * connection is established before <CODE>open</CODE> returns.
 * If the secure connection cannot be established due to errors
 * related to certificates a <CODE>CertificateException</CODE> is thrown.
 * 
 * <P>
 * A secure socket is accessed using a generic connection string 
 * with an explicit host and port number. The host may be specified 
 * as a fully qualified host name or 
 * IPv4 number.
 * e.g. <code>ssl://host.com:79</code> defines a target socket on the 
 * <code>host.com</code> system at 
 * port <code>79</code>.
 * <P>Note that 
 * RFC1900 recommends the use of names rather than IP numbers for best results
 * in the event of IP number reassignment. </P>
 * <P>
 * A secure connection MUST be implemented by one or more
 * of the following specifications:
 * <UL>
 *   <LI>TLS Protocol Version 1.0 as specified in
 *   <A HREF="http://www.ietf.org/rfc/rfc2246.txt">RFC 2246</A>.
 *   </LI>
 *
 *   <LI>SSL V3 as specified in
 *     <A HREF="http://home.netscape.com/eng/ssl3/draft302.txt">
 *     The SSL Protocol Version 3.0</A>
 *   </LI>
 *
 *   <LI>WAP(TM) TLS Profile and Tunneling Specification as specified
 *	in <A HREF="http://www.wapforum.com/what/technical.htm">
 *	WAP-219-TLS-20010411-a</A>
 *   </LI>
 * </UL>
 *
 * <H2>
 * BNF Format for Connector.open() string
 * </H2>
 * <P>
 * The URI must conform to the BNF syntax specified below.  If the URI
 * does not conform to this syntax, an <code>IllegalArgumentException</code>
 * is thrown.
 * </P>
 * <TABLE BORDER="1">
 * <TR>
 * <TD>&lt;socket_connection_string&gt; </TD>
 * <TD>::= "<strong>ssl://</strong>"&lt;hostport&gt; </TD>
 * </TR>
 * <TR>
 * <TD>&lt;hostport&gt; </TD>
 * <TD>::= <I>host</I> ":" <I>port </I> </TD>
 * </TR>
 * <TR>
 * <TD>&lt;host&gt; </TD>
 * <TD>::= <I>host name or IP address </I>
 * </TD>
 * </TR>
 * <TR>
 * <TD>&lt;port&gt; </TD>
 * <TD>::= <I>numeric port number </I> </TD>
 * </TR>
 * </TABLE>
 *
 * <H2>
 * Examples
 * </H2>
 * <P>
 * The following examples show how a <code>SecureConnection</code>
 * would be used to access a sample loopback program.
 * </P>
 * <PRE>
 *   SecureConnection sc = (SecureConnection)
 *                         Connector.open("ssl://host.com:79");
 *   SecurityInfo info = sc.getSecurityInfo();
 *   boolean isTLS = (info.getProtocolName().equals("TLS"));
 * 
 *   sc.setSocketOption(SocketConnection.LINGER, 5);
 *
 *   InputStream is  = sc.openInputStream();
 *   OutputStream os = sc.openOutputStream();
 *
 *   os.write("\r\n".getBytes());
 *   int ch = 0;
 *   while(ch != -1) {
 *       ch = is.read();
 *   }
 *
 *   is.close();
 *   os.close();
 *   sc.close();
 * </PRE>
 *
 * @since MIDP 2.0
 */
public interface SecureConnection extends SocketConnection {
    /**
     * Return the security information associated with this connection
     * when it was opened.
     *
     * @return the security information associated with this open connection.
     * @exception IOException if an arbitrary connection failure occurs
     */
    public SecurityInfo getSecurityInfo() throws IOException;
}
