package service;

import java.io.IOException;
import java.util.UUID;

public interface HttpRequesterService {
    UUID exec(int threads, int loop) throws IOException;
}
