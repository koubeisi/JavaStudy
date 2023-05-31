package bio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author KouBeisi
 * @since 2021/6/3
 */
@Slf4j
class ObjectStreamTest {

    @Test
    void objectOutputStreamTest() {
        var employee = new Employee("Tom", "123@qq.com");

        try (var fileOutputStream = new FileOutputStream("file/employee.dat");
             var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(employee);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void objectInputStreamTest(){
        try (var fileInputStream = new FileInputStream("file/employee.dat");
             var objectInputStream = new ObjectInputStream(fileInputStream)) {
            var employee = (Employee) objectInputStream.readObject();
            log.info(employee.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

@Data
@AllArgsConstructor
class Employee implements Serializable {
    private String name;
    private String email;
}
