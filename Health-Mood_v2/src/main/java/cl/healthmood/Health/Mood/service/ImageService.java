package cl.healthmood.Health.Mood.service;

import cl.healthmood.Health.Mood.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<Image> getAllImages();
    Optional<Image> getImageById(Integer id);
    Image saveImage(Image image);
    void deleteImage(Integer id);
}