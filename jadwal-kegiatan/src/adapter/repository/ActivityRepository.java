package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepository implements IActivityRepository {
    private final List<Activity> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Activity> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Activity> findById(int id) {
        return data.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public Activity save(String title, String day, String time) {
        Activity activity = new Activity(++idCounter, title, day, time);
        data.add(activity);
        return activity;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(a -> a.getId() == id);
    }

    @Override
    public boolean update(Activity activity) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == activity.getId()) {
                data.set(i, activity);
                return true;
            }
        }
        return false;
    }
}
