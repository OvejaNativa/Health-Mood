package cl.healthmood.Health.Mood.service;
import cl.healthmood.Health.Mood.model.Image;
import cl.healthmood.Health.Mood.repository.ImageRepository;
import cl.healthmood.Health.Mood.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    // Constructor injection sin @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> getImageById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}