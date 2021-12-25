package API;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.DocsScopes;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Color;
import com.google.api.services.docs.v1.model.DeleteContentRangeRequest;
import com.google.api.services.docs.v1.model.Dimension;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.docs.v1.model.InsertTextRequest;
import com.google.api.services.docs.v1.model.Link;
import com.google.api.services.docs.v1.model.Location;
import com.google.api.services.docs.v1.model.OptionalColor;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.RgbColor;
import com.google.api.services.docs.v1.model.TextStyle;
import com.google.api.services.docs.v1.model.UpdateTextStyleRequest;
import com.google.api.services.docs.v1.model.WeightedFontFamily;

/* class to demonstarte use of Docs get documents API */
public class GoogleDocAPI {
	/** Application name. */
	public static final String APPLICATION_NAME = "Google Docs API Java Quickstart";
	/** Global instance of the JSON factory. */
	public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	/** Directory to store authorization tokens for this application. */
	public static final String TOKENS_DIRECTORY_PATH = "tokens";

	public static Docs service;

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	public static final List<String> SCOPES = Collections.singletonList(DocsScopes.DOCUMENTS);
	public static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = GoogleDocAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		// returns an authorized Credential object.
		return credential;
	}

	public static void getTitle(String id) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		// Prints the title of the requested doc:
		// https://docs.google.com/document/d/195j9eDD3ccgjQRttHhJPymLJUCOUjs-jmwTrekvdjFE/edit
		Document response = service.documents().get(id).execute();
		String title = response.getTitle();

		System.out.printf("The title of the doc is: %s\n", title);
	}

	private static String createDoc(Docs service, String title) throws IOException {
		Document doc = new Document().setTitle(title);
		doc = service.documents().create(doc).execute();
		System.out.println("Created document with title: " + doc.getTitle());

		return doc.getDocumentId();
	}

	public static void write(String id, String s1) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request()
				.setInsertText(new InsertTextRequest().setText(s1).setLocation(new Location().setIndex(1))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(id, body).execute();
	}
	
	
	public static void replace() throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(1).setEndIndex(18))));


		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents()
				.batchUpdate("1vzAq9V-5DxNGjE6miC-TJdXy-ghEeV6H2p7CWUUoAKk", body).execute();

		write("1vzAq9V-5DxNGjE6miC-TJdXy-ghEeV6H2p7CWUUoAKk", "Ensun GMBH");
	}
	
	public static void format() throws GeneralSecurityException, IOException {
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		
		  List<Request> requests = new ArrayList<>();
	        requests.add(new Request().setUpdateTextStyle(new UpdateTextStyleRequest()
	                .setTextStyle(new TextStyle()
	                        .setBold(true)
	                        .setItalic(true))
	                .setRange(new Range()
	                        .setStartIndex(1)
	                        .setEndIndex(11))
	                .setFields("bold")));

	        BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
	        BatchUpdateDocumentResponse response = service.documents()
	                .batchUpdate("1vzAq9V-5DxNGjE6miC-TJdXy-ghEeV6H2p7CWUUoAKk", body).execute();
	}
	
	public static void main(String[] args) throws IOException, GeneralSecurityException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		getTitle("1yWITCM79ypOEgw39feTa0cxhJGcSk_3wXaUZbKE44V4");

//		String id = createDoc(service, "Testing!");

//		System.out.println(id);

		write("1vzAq9V-5DxNGjE6miC-TJdXy-ghEeV6H2p7CWUUoAKk",
				"Mohammed Ali Anis" + System.lineSeparator() + "Haya Anis" + System.lineSeparator() + "Ehab Anis"
						+ System.lineSeparator() + "Abdulbaset Anis" + System.lineSeparator() + "Nada Kassab");
		
		replace();
		
		format();
		
		

	}
}
