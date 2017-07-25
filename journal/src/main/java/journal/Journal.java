package journal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Journal<T> {
  private FileChannel channel;
  private SerDe<T> serde;
  private Writer writer;

  public Journal(File file, SerDe<T> serde) throws IOException {
    channel = new FileInputStream(file).getChannel();
    this.serde = serde;
  }

  public Writer writer() throws IOException {
    return new Writer();
  }

  class Writer {
    MappedByteBuffer buffer;

    Writer() throws IOException {
        buffer = channel.map(FileChannel.MapMode.READ_WRITE, channel.position(), 0);

    }

    public void write(T item) {
      buffer.put(serde.serialize(item));
    }
  }

  class Reader {
    public T read() {
      return null;
    }
  }


}