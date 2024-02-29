package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.BuildingEditDTO;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.searchOutput.BuildingSearchOutput;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageUtils messageUtil;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingDTO buildingDTO, HttpServletRequest request) throws IllegalAccessException {
        ModelAndView mav = new ModelAndView("admin/building/building-list");
        DisplayTagUtils.of(request, buildingDTO);

        mav.addObject("modelSearch", buildingDTO);
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("typemaps", buildingService.getType());
        mav.addObject("typemapsbyconstant", BuildingTypeUtils.getTypes());
        mav.addObject("districtmaps", buildingService.getDistrict());
        mav.addObject("districtmapsbyconstant", DistrictUtils.getDistrict());


        if(SecurityUtils.getAuthorities().contains(SystemConstant.USER_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingDTO.setStaffId(staffId);
        }

        List<BuildingSearchOutput> buildingSearchOutputs = buildingService.findBuilding(buildingDTO, new PageRequest(buildingDTO.getPage() - 1, buildingDTO.getMaxPageItems()));
        BuildingSearchOutput buildingSearchOutput = new BuildingSearchOutput();
        buildingSearchOutput.setListResult(buildingSearchOutputs);
        List<BuildingSearchOutput> listOfTotalResults = buildingService.search(buildingDTO);
        buildingSearchOutput.setTotalItems(listOfTotalResults.size());
        mav.addObject("buildingSearch", buildingSearchOutput);

        initMessageResponse(mav, request);

        return mav;
    }

    @RequestMapping(value="/admin/building-edit-{id}", method=RequestMethod.GET)
    public ModelAndView buildingAddGetList(@ModelAttribute("modelEdit") BuildingEditDTO buildingEditDTO,
                                           @PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/building/building-edit");
        mav.addObject("typemapsbyconstant", BuildingTypeUtils.getTypes());
        mav.addObject("districtmapsbyconstant", DistrictUtils.getDistrict());
        if(ValidateUtils.isValid(id)){
            mav.addObject("modelEdit", buildingService.findById(id));
        }else{
            mav.addObject("modelEdit", buildingEditDTO);
        }

        return mav;
    }

    @RequestMapping(value="/admin/building-edit", method=RequestMethod.POST)
    public ModelAndView buildingAddPostList(@ModelAttribute("modelEdit") BuildingEditDTO buildingEditDTO){
        ModelAndView mav = new ModelAndView("admin/building/building-edit");

        return mav;
    }


    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
