package nc.api.admin.impl.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSManager extends SSLSocketFactory implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager, HostnameVerifier {
	final static String [] SSLProtocols = {"SSLv2Hello", "SSLv3", "TLSv1" }; 
	SSLContext sslContext;
	SSLSocketFactory localSocketFactory;
	public TLSManager() throws Exception{
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		javax.net.ssl.TrustManager[] tmCerts = new javax.net.ssl.TrustManager[] { this};
		sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, tmCerts, new SecureRandom());
		localSocketFactory = sslContext.getSocketFactory();
	}
	
	public SSLContext getSSLContext(){
		return sslContext;
	}
	@Override
	public void checkClientTrusted(X509Certificate[] ax509certificate, String s)
			throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] ax509certificate, String s)
			throws CertificateException {
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	@Override
	public boolean verify(String paramString, SSLSession paramSSLSession) {
		return true;
	}

	//SSLSocketFactory ---
	@Override
	public String[] getDefaultCipherSuites() {
		return localSocketFactory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return localSocketFactory.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(Socket paramSocket, String paramString,
			int paramInt, boolean paramBoolean) throws IOException {
		SSLSocket socket = (SSLSocket) localSocketFactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
		socket.setEnabledProtocols(SSLProtocols);
		return socket;
	}

	@Override
	public Socket createSocket(String paramString, int paramInt)
			throws IOException, UnknownHostException {
		SSLSocket socket = (SSLSocket) localSocketFactory.createSocket(paramString, paramInt);
		socket.setEnabledProtocols(SSLProtocols);
		return socket;
	}

	@Override
	public Socket createSocket(InetAddress paramInetAddress, int paramInt)
			throws IOException {
		SSLSocket socket = (SSLSocket) localSocketFactory.createSocket(paramInetAddress, paramInt);
		socket.setEnabledProtocols(SSLProtocols);
		return socket;
	}

	@Override
	public Socket createSocket(String paramString, int paramInt1,
			InetAddress paramInetAddress, int paramInt2) throws IOException,
			UnknownHostException {
		SSLSocket socket = (SSLSocket) localSocketFactory.createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
		socket.setEnabledProtocols(SSLProtocols);
		return socket;
	}

	@Override
	public Socket createSocket(InetAddress paramInetAddress1, int paramInt1,
			InetAddress paramInetAddress2, int paramInt2) throws IOException {
		SSLSocket socket = (SSLSocket) localSocketFactory.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
		socket.setEnabledProtocols(SSLProtocols);
		return socket;
	}
}
