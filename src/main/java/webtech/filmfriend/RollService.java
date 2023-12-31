package webtech.filmfriend;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class RollService {
    @Autowired
    RollRepo rollRepo;
    @Autowired
    CameramodelRepo cameraRepository;
    @Autowired
    StockRepository stockRepo;

    @Transactional
    public Roll assignRollToCamera(Long cameraId, Long stockId) {
        Cameramodel camera = cameraRepository.findById(cameraId).orElseThrow(() -> new RuntimeException("Camera not found"));
        if (camera != null && camera.getRoll() == null) {
            Roll newRoll = createNewRoll(stockId);
            camera.setRoll(newRoll);
            cameraRepository.save(camera);
            return newRoll;
        }
        throw new RuntimeException("Camera already has a roll");
    }

    public Roll getRoll(Long rollId) {
        return rollRepo.findById(rollId).orElseThrow(() -> new RuntimeException("Roll not found"));
    }

    public Iterable <Roll> getAllRolls() {
        return rollRepo.findAll();
    }

    public Iterable<Roll> getRollsInDevelopment() {
        return StreamSupport.stream(getAllRolls().spliterator(), false).filter(roll -> roll.getExpectedPickupDate() != null).toList();
    }

    private Roll createNewRoll(Long stockId) {
        Roll roll = new Roll();
        Stock stock = stockRepo.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
        roll.setStock(stock);
        rollRepo.save(roll);
        return roll;
    }

    public Roll developRoll(Long cameraId, LocalDate expectedPickupDate, int usedIso, String labName, String notes) {
        Cameramodel camera = cameraRepository.findById(cameraId).orElseThrow(() -> new RuntimeException("Camera not found"));
        Roll roll = camera.getRoll();
        if (roll == null) {
            throw new RuntimeException("Camera has no roll");
        }
        roll.setExpectedPickupDate(expectedPickupDate);
        roll.setUsedIso(usedIso);
        roll.setLabName(labName);
        roll.setNotes(notes);
        rollRepo.save(roll);
        camera.setRoll(null);
        cameraRepository.save(camera);
        return roll;
    }

    public Roll deleteRollFromCamera(Long cameraId) {
        Cameramodel camera = cameraRepository.findById(cameraId).orElseThrow(() -> new RuntimeException("Camera not found"));
        Roll roll = camera.getRoll();
        Long id = roll.getId();
        camera.setRoll(null);
        rollRepo.deleteById(id);
        cameraRepository.save(camera);
        return roll;
    }

    public Roll deleteRoll(Long rollId) {
        Roll roll = rollRepo.findById(rollId).orElseThrow(() -> new RuntimeException("Roll not found"));
        rollRepo.delete(roll);
        return roll;
    }
}
