package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

public class CsvRideRepository implements RideRepository {
    private final String fileName;

    public CsvRideRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(RideReceipt receipt) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(receipt.getBikeId() + "," + receipt.getMinutes() + "," + receipt.getFee() + "\n");
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write ride to " + fileName, e);
        }
    }
}
