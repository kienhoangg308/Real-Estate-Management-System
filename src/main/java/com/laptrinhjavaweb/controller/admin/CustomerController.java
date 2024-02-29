package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.*;
import com.laptrinhjavaweb.dto.searchOutput.BuildingSearchOutput;
import com.laptrinhjavaweb.dto.searchOutput.CustomerSearchOutput;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.service.impl.TransactionService;
import com.laptrinhjavaweb.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageUtils messageUtil;

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") CustomerDTO customerDTO, HttpServletRequest request) throws IllegalAccessException {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        DisplayTagUtils.of(request, customerDTO);

        //######## OLD CODE/
        mav.addObject("modelSearch", customerDTO);
        mav.addObject("staffmaps", userService.getStaffMaps());


        if(SecurityUtils.getAuthorities().contains(SystemConstant.USER_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerDTO.setStaffId(staffId);
        }

        List<CustomerSearchOutput> customerSearchOutputs = customerService.findCustomer(customerDTO, new PageRequest(customerDTO.getPage() - 1, customerDTO.getMaxPageItems()));
        CustomerSearchOutput customerSearchOutput = new CustomerSearchOutput();
        customerSearchOutput.setListResult(customerSearchOutputs);
        List<CustomerSearchOutput> listOfTotalResults = customerService.search(customerDTO);
        customerSearchOutput.setTotalItems(listOfTotalResults.size());
        //mav.addObject("buildingSearch", buildingSearchOutput);
        mav.addObject("customerSearch", customerSearchOutput);

        initMessageResponse(mav, request);

        return mav;
    }

    @RequestMapping(value="/admin/customer-edit-{id}", method= RequestMethod.GET)
    public ModelAndView buildingAddGetList(@ModelAttribute("modelEdit") CustomerEditDTO customerEditDTO,
                                           @PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        mav.addObject("transactionmapbyconstant", TransactionUtils.getTransaction());
        mav.addObject("transactions", transactionService.getOutput(id));
        if(ValidateUtils.isValid(id)){
            mav.addObject("modelEdit", customerService.findById(id));
        }else{
            mav.addObject("modelEdit", customerEditDTO);
        }

        return mav;
    }

    @RequestMapping(value="/admin/customer-edit", method= RequestMethod.GET)
    public ModelAndView customerAddGetList(@ModelAttribute("modelEdit") CustomerEditDTO customerEditDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        mav.addObject("modelEdit", customerEditDTO);

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
