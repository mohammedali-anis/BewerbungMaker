package API;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
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
import com.google.api.services.drive.Drive;

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

//	public static void write2(String id, String s1) throws GeneralSecurityException, IOException {
//		// Build a new authorized API client service.
//		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
//		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
//				.setApplicationName(APPLICATION_NAME).build();
//
//		List<Request> requests2 = new ArrayList<>();
//		requests2.add(new Request()
//				.setInsertText(new InsertTextRequest().setText(s1).setLocation(new Location().setIndex(53))));
//
//		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
//		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(id, body2).execute();
//	}
//
//	public static void rep2() throws GeneralSecurityException, IOException {
//
//		write2("1n2k5aDKTd2V6QW5XTH4swD0iOxETXjSQhE0DDihhKIM", "HIIIIIIIIII");
//	}

	public static void lastName_AN(String lastName, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(43).setEndIndex(44))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(lastName).setLocation(new Location().setIndex(43))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void firstName_AN(String firstName, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(41).setEndIndex(42))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(firstName).setLocation(new Location().setIndex(41))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void sex_AN(String sex, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(39).setEndIndex(40))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(sex).setLocation(new Location().setIndex(39))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallFirstName(String smallFirstName, String docID)
			throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(11).setEndIndex(12))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request().setInsertText(
				new InsertTextRequest().setText(smallFirstName).setLocation(new Location().setIndex(11))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallLastName(String smallLastName, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(13).setEndIndex(14))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request().setInsertText(
				new InsertTextRequest().setText(smallLastName).setLocation(new Location().setIndex(13))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallStreet(String smallStreet, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(17).setEndIndex(18))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(smallStreet).setLocation(new Location().setIndex(17))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallNo(String smallNo, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(19).setEndIndex(20))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(smallNo).setLocation(new Location().setIndex(19))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallPLZ(String smallPLZ, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(21).setEndIndex(22))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(smallPLZ).setLocation(new Location().setIndex(21))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void smallCity(String smallCity, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(23).setEndIndex(24))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(smallCity).setLocation(new Location().setIndex(23))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void sexAN(String sexAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(25).setEndIndex(26))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(sexAN).setLocation(new Location().setIndex(25))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void firstNameAN(String firstNameAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(27).setEndIndex(28))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(firstNameAN).setLocation(new Location().setIndex(27))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void lastNameAN(String lastNameAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(29).setEndIndex(30))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(lastNameAN).setLocation(new Location().setIndex(29))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void streetAN(String streetAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(31).setEndIndex(33))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(streetAN).setLocation(new Location().setIndex(31))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void noAN(String noAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(34).setEndIndex(36))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(noAN).setLocation(new Location().setIndex(34))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void plzAN(String plzAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(37).setEndIndex(39))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(plzAN + ", ").setLocation(new Location().setIndex(37))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void stadtAN(String stadtAN, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(40).setEndIndex(42))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(stadtAN).setLocation(new Location().setIndex(40))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static boolean exportPDF(String docID) throws URISyntaxException {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

		URI link = new URI("https://docs.google.com/document/d/" + docID + "/export?format=pdf");

		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(link);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void run(String stadtAN, String plzAN, String noAN, String streetAN, String lastNameAN,
			String firstNameAN, String sexAN, String smallCity, String smallPLZ, String smallNo, String smallStreet,
			String smallLastName, String smallFirstName, String MyEmail, String MyMobile, String MyCity, String MyPLZ,
			String MyNo, String MyStreet, String MyLastName, String MyFirstName)
			throws GeneralSecurityException, IOException, URISyntaxException {
		String docID = "1n2k5aDKTd2V6QW5XTH4swD0iOxETXjSQhE0DDihhKIM";

		MyEmail(MyEmail, docID);
		MyMobile(MyMobile, docID);
		MyCity(MyCity, docID);
		MyPLZ(MyPLZ, docID);
		MyNo(MyNo, docID);
		MyStreet(MyStreet, docID);
		MyLastName(MyLastName, docID);
		MyFirstName(MyFirstName, docID);

		stadtAN(stadtAN, docID);
		plzAN(plzAN, docID);
		noAN(noAN, docID);
		streetAN(streetAN, docID);
		lastNameAN(lastNameAN, docID);
		firstNameAN(firstNameAN, docID);
		sexAN(sexAN, docID);

		smallCity(smallCity, docID);
		smallPLZ(smallPLZ, docID);
		smallNo(smallNo, docID);
		smallStreet(smallStreet, docID);
		smallLastName(smallLastName, docID);
		smallFirstName(smallFirstName, docID);

//		exportPDF(docID);

	}

	public static void MyFirstName(String MyFirstName, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(50).setEndIndex(52))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyFirstName).setLocation(new Location().setIndex(50))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyLastName(String MyLastName, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(53).setEndIndex(55))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyLastName).setLocation(new Location().setIndex(53))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyStreet(String MyStreet, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(56).setEndIndex(58))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyStreet).setLocation(new Location().setIndex(56))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyNo(String MyNo, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(59).setEndIndex(61))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyNo).setLocation(new Location().setIndex(59))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyPLZ(String MyPLZ, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(62).setEndIndex(64))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyPLZ + ",").setLocation(new Location().setIndex(62))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyCity(String MyCity, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(65).setEndIndex(67))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyCity).setLocation(new Location().setIndex(65))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyMobile(String MyMobile, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(68).setEndIndex(70))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyMobile).setLocation(new Location().setIndex(68))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void MyEmail(String MyEmail, String docID) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Docs service = new Docs.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests = new ArrayList<>();
		requests.add(new Request().setDeleteContentRange(
				new DeleteContentRangeRequest().setRange(new Range().setStartIndex(71).setEndIndex(73))));

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = service.documents().batchUpdate(docID, body).execute();

		final NetHttpTransport HTTP_TRANSPORT2 = GoogleNetHttpTransport.newTrustedTransport();
		Docs service2 = new Docs.Builder(HTTP_TRANSPORT2, JSON_FACTORY, getCredentials(HTTP_TRANSPORT2))
				.setApplicationName(APPLICATION_NAME).build();

		List<Request> requests2 = new ArrayList<>();
		requests2.add(new Request()
				.setInsertText(new InsertTextRequest().setText(MyEmail).setLocation(new Location().setIndex(71))));

		BatchUpdateDocumentRequest body2 = new BatchUpdateDocumentRequest().setRequests(requests2);
		BatchUpdateDocumentResponse response2 = service2.documents().batchUpdate(docID, body2).execute();

	}

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		String docID = "1n2k5aDKTd2V6QW5XTH4swD0iOxETXjSQhE0DDihhKIM";

	}
}
