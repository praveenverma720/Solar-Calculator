package com.praveen.gooded.modals;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PinsViewModal extends AndroidViewModel {
    private PinsRepository pinsRepository;

    private LiveData<List<Pins>> allPins;

    public PinsViewModal(Application application) {
        super(application);
        pinsRepository = new PinsRepository(application);
        allPins = pinsRepository.getAllWords();
    }

    public LiveData<List<Pins>> getAllWords() { return allPins; }

    public void insert(Pins pin) {
        pinsRepository.insert(pin);
    }
}
