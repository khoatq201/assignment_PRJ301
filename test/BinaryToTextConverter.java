import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class BinaryToTextConverter {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\Admin\\Downloads\\EOSClient_23_Mar_2024\\EOSClient_23_Mar_2024\\MAS291_SU24_FE_165303\\khoatqse184056.dat"; // Đường dẫn đến file .dat
        String outputFilePath = "output.txt"; // Đường dẫn đến file output

        try (FileInputStream fis = new FileInputStream(inputFilePath)) {
            // Đọc dữ liệu nhị phân từ file
            byte[] binaryData = readAllBytes(fis);

            // Chuyển đổi dữ liệu nhị phân thành văn bản
            String textData = new String(binaryData, "UTF-8");

            // Ghi dữ liệu văn bản vào file
            try (FileWriter fw = new FileWriter(outputFilePath)) {
                fw.write(textData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương pháp đọc tất cả byte từ InputStream
    private static byte[] readAllBytes(FileInputStream fis) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }
}
