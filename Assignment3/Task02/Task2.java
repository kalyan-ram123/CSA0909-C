import java.io.IOException;

class AssignmentException extends Exception {
    AssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}

class UploadException extends AssignmentException {
    UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}

class AssignmentStorage {
    void upload(String fileName, int sizeKb) throws UploadException {
        try {
            if (sizeKb > 500) {
                throw new IOException("The sample storage limit is 500 KB.");
            }
            System.out.println("Uploaded: " + fileName);
        } catch (IOException ex) {
            throw new UploadException("Unable to upload " + fileName, ex);
        }
    }
}

public class Task2 {
    public static void main(String[] args) {
        AssignmentStorage storage = new AssignmentStorage();
        try {
            storage.upload("submission1.pdf", 250);
            storage.upload("submission2.pdf", 750);
        } catch (AssignmentException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Original cause: " + ex.getCause().getMessage());
        }
    }
}
