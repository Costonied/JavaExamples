package ru.costonied.examples.google.credentials;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * <p>Factory helps get Google Credentials for different account types<ul>
 * <li> User account
 * <li> Service account
 * </ul></p>
 * More details about using OAuth 2.0 to access Google API:
 * <a href="https://developers.google.com/identity/protocols/oauth2">
 * developers.google.com-oauth2</a>
 */
public class CredentialFactory {
    private static final String USER_ACCOUNT_CREDENTIALS = "/user-acc-credentials.json";
    private static final String SERVICE_ACCOUNT_CREDENTIALS = "/service-acc-credentials.json";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

    /**
     * Getting user account credentials for specific user.
     * While authorization process user receive authentication page where should select own account.
     * After user accept authorization request the approve will be sent back to LocalServerReceiver.
     */
    public Credential getUserAccountCredential(String googleUserId) throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = getCredentialsInputStream(USER_ACCOUNT_CREDENTIALS);
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));
        in.close();
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(googleUserId);
    }

    /**
     * Getting credentials for Google Service account.
     */
    public Credential getServiceAccountCredential() throws IOException {
        InputStream in = getCredentialsInputStream(SERVICE_ACCOUNT_CREDENTIALS);
        GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(SCOPES);
        in.close();
        return credential;
    }

    private InputStream getCredentialsInputStream(String credentialsFileName) throws FileNotFoundException {
        InputStream in = CredentialFactory.class.getResourceAsStream(credentialsFileName);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + credentialsFileName);
        } else {
            return in;
        }
    }
}
