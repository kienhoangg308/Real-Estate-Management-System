package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.searchOutput.RentAreaSearchOutput;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {

    @Autowired
    private ModelMapper modelMapper;

    public List<RentAreaSearchOutput> convertToRentAreaSearchOutputs (List<RentAreaEntity> rentAreaEntities){
        List<RentAreaSearchOutput> results = new ArrayList<>();
        for(RentAreaEntity rentAreaEntity : rentAreaEntities){
            RentAreaSearchOutput rentAreaSearchOutput = modelMapper.map(rentAreaEntity, RentAreaSearchOutput.class);
            results.add(rentAreaSearchOutput);
        }
        return results;
    }
}
