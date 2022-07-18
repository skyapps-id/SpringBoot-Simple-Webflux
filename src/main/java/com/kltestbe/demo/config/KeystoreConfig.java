package com.kltestbe.demo.config;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class KeystoreConfig {
	private static final Logger log = LoggerFactory.getLogger(KeystoreConfig.class);

	@Value("${app.jwt.keystore-location}")
	private String keyStorePath;

	@Value("${app.jwt.keystore-password}")
	private String keyStorePassword;

	@Value("${app.jwt.key-alias}")
	private String keyAlias;

	@Value("${app.jwt.private-key-passphrase}")
	private String privateKeyPassphrase;

	@Bean
	public KeyStore getKeyStore() {
		try {
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(keyStorePath);
			keyStore.load(resourceAsStream, keyStorePassword.toCharArray());
			return keyStore;
		} catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
			log.error("Unable to load keystore: {}", keyStorePath, e);
		}

		throw new IllegalArgumentException("Unable to load keystore");
	}

	@Bean
	public RSAPrivateKey getJwtSigningPrivateKey() {
		try {
			Key key = getKeyStore().getKey(keyAlias, privateKeyPassphrase.toCharArray());
			if (key instanceof RSAPrivateKey) {
				return (RSAPrivateKey) key;
			}
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
			log.error("Unable to load private key from keystore: {}", keyStorePath, e);
		}

		throw new IllegalArgumentException("Unable to load private key");
	}

	@Bean
	public RSAPublicKey getJwtValidationPublicKey(KeyStore keyStore) {
		try {
			Certificate certificate = keyStore.getCertificate(keyAlias);
			PublicKey publicKey = certificate.getPublicKey();

			if (publicKey instanceof RSAPublicKey) {
				return (RSAPublicKey) publicKey;
			}
		} catch (KeyStoreException e) {
			log.error("Unable to load private key from keystore: {}", keyStorePath, e);
		}

		throw new IllegalArgumentException("Unable to load RSA public key");
	}

	@Bean
	public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
		return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
	}
}
