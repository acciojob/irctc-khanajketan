package com.driver.transformer;

import com.driver.EntryDto.AddTrainEntryDto;
import com.driver.model.Station;
import com.driver.model.Train;

import java.util.List;

public class TrainTransformer {

    public static Train addTrainEntryDtoToTrain(AddTrainEntryDto addTrainEntryDto){
        Train train = new Train();
        train.setDepartureTime(addTrainEntryDto.getDepartureTime());
        train.setNoOfSeats(addTrainEntryDto.getNoOfSeats());
        List<Station> stationList = addTrainEntryDto.getStationRoute();
        String route = "";
        for(Station station: stationList){
            if(route.compareTo("") == 0)route = route + station;
            else route = route + "," +station;
        }
        train.setRoute(route);

        return train;

    }
}
