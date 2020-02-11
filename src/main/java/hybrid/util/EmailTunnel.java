package hybrid.util;
/*
 * package hybrid.util;
 * 
 * import javax.mail.*; import javax.mail.internet.InternetAddress; import
 * javax.mail.internet.MimeMessage; import java.io.UnsupportedEncodingException;
 * import java.util.Arrays; import java.util.List; import java.util.Properties;
 * import java.util.stream.Collectors;
 * 
 * public class EmailTunnel {
 * 
 * private static final String IMAP_HOST = "imap.gmail.com"; private static
 * final String IMAP_PROTOCOL = "imap";
 * 
 * private static final String SMTP_HOST = "smtp.gmail.com"; private static
 * final String SMTP_PORT = "587";
 * 
 * private static final int MAX_RECENT_MESSAGES_TO_SEARCH = 1000; private static
 * final String INBOX_FOLDER = "INBOX";
 * 
 * private static final String TEST_EMAIL_SUBJECT =
 * "Hybrid Automation Test Email";
 * 
 * private final Session session; private final String emailAddress; private
 * final String password; private Store store;
 * 
 * public EmailTunnel(String emailAddress, String password) {
 * 
 * this.emailAddress = emailAddress; this.password = password;
 * 
 * final Properties props = new Properties();
 * props.setProperty("mail.imap.ssl.enable", "true");
 * props.put("mail.smtp.starttls.enable", true); props.put("mail.smtp.host",
 * SMTP_HOST); props.put("mail.smtp.user", emailAddress);
 * props.put("mail.smtp.password", password); props.put("mail.smtp.port",
 * SMTP_PORT); props.put("mail.smtp.auth", true);
 * 
 * session = Session.getDefaultInstance(props, new Authenticator() { protected
 * PasswordAuthentication getPasswordAuthentication() { return new
 * PasswordAuthentication( emailAddress, password); } }); }
 * 
 * public EmailTunnel connect() { try { store = session.getStore(IMAP_PROTOCOL);
 * store.connect(IMAP_HOST, this.emailAddress, this.password); } catch
 * (Exception e) { throw new
 * RuntimeException("Unable to connect with provided email account credentials ("
 * + this.emailAddress +
 * ". Please check email properties supplied and try again"); }
 * 
 * return this; }
 * 
 * public void disconnect() throws MessagingException { store.close(); }
 * 
 * public void sendTestEmail() throws MessagingException,
 * UnsupportedEncodingException { String msgBody = "This is a test email!";
 * 
 * Message msg = new MimeMessage(session); msg.setFrom(new
 * InternetAddress("roshanjames75@gmail.com", "Automation Tester"));
 * msg.addRecipient(Message.RecipientType.TO, new
 * InternetAddress(this.emailAddress, "Test email recipient"));
 * msg.setSubject(TEST_EMAIL_SUBJECT); msg.setText(msgBody);
 * Transport.send(msg); }
 * 
 * public void deleteTestEmails() throws MessagingException { final Folder inbox
 * = store.getFolder(INBOX_FOLDER); inbox.open(Folder.READ_WRITE);
 * 
 * Arrays.stream(inbox.getMessages()) .filter(this::isTestEmail)
 * .forEach(this::deleteMessage);
 * 
 * inbox.close(true); }
 * 
 * public List<Message> getTestEmails() throws MessagingException { final Folder
 * inbox = store.getFolder(INBOX_FOLDER); inbox.open(Folder.READ_ONLY);
 * 
 * List<Message> messages = Arrays.asList(inbox.getMessages());
 * 
 * messages = messages.stream() .limit(MAX_RECENT_MESSAGES_TO_SEARCH)
 * .filter(this::isTestEmail) .collect(Collectors.toList());
 * 
 * inbox.close(false);
 * 
 * return messages; }
 * 
 * 
 * private void deleteMessage(final Message message) { try {
 * message.setFlag(Flags.Flag.DELETED, true); } catch (final MessagingException
 * e) { e.printStackTrace(); } }
 * 
 * 
 * private boolean isTestEmail(Message message) { try { return
 * message.getSubject().equals(TEST_EMAIL_SUBJECT); } catch (MessagingException
 * e) { e.printStackTrace(); return false; } } }
 * 
 */