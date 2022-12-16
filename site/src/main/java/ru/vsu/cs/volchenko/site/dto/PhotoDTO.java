package ru.vsu.cs.volchenko.site.dto;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.cs.volchenko.site.models.Photo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
@Getter
@Setter
@Component
public class PhotoDTO {

    private int productId;

    private List<BufferedImage> productPhotos;

    public void addPhoto(BufferedImage image) {
        productPhotos.add(image);
    }

    /*public List<Photo> toPhotos() { //todo: blob creation, connection
        AtomicInteger index = new AtomicInteger(0);
        return productPhotos.stream()
                .map(productPhoto -> {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(productPhoto, "jpg", baos);
                    byte[] imageInByte = baos.toByteArray();
                    Blob blob = jdbcTemplate();
                    blob.setBytes(1, imageInByte);
                    return new Photo(productId, index.incrementAndGet(), productPhoto.)
                })
    }*/

}
