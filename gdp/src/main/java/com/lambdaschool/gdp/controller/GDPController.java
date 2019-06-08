package com.lambdaschool.gdp.controller;

import com.lambdaschool.gdp.GdpApplication;
import com.lambdaschool.gdp.exception.ResourceNotFoundException;
import com.lambdaschool.gdp.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static com.lambdaschool.gdp.GdpApplication.gdpList;

@EnableWebMvc
@RestController

public class GDPController {
    private static final Logger logger = LoggerFactory.getLogger(GDPController.class);

    //    localhost:3000/country/2 - return using the JSON format a single country and GDP based off of its id number
    @GetMapping(value="/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable long id, HttpServletRequest request){
        logger.info(request.getRequestURI() + " accessed");
        if(gdpList.findGDP((o)->o.getId()==id) == null){
            logger.info(request.getRequestURI() + " threw an error");

            throw new ResourceNotFoundException("No country found with "+ id + " id");
        }
        GDP country = gdpList.findGDP((o)->o.getId()==id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
    // localhost:3000/country/stats/median - return using the JSON the country and its GDP with the median GDP
    @GetMapping(value="/country/stats/median")
    public ResponseEntity<?> getMedianGDP(HttpServletRequest request){
        logger.info(request.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = gdpList.gdpList;
        gdplist.sort((g1,g2)-> g2.getGdp() - g1.getGdp());
        return new ResponseEntity<>(gdplist.get(gdplist.size()/2), HttpStatus.OK);
    }
    // localhost:3000//country/economy - return using the JSON format all of the countries sorted from most to least GDP
    @GetMapping(value="/country/economy")
    public ResponseEntity<?> getMostToLeastGDP(HttpServletRequest res){
        logger.info(res.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = gdpList.gdpList;
        gdplist.sort((o1,o2)-> o2.getGdp() - o1.getGdp());
        return new ResponseEntity<>(gdplist, HttpStatus.OK);
    }
    // localhost:3000/names - return using the JSON format all of the countries alphabetized by name
    @GetMapping(value="/names")
    public ResponseEntity<?> sortAlphabetically(HttpServletRequest request){
        logger.info(request.getRequestURI() + " accessed");

        ArrayList<GDP> gdplist = GdpApplication.gdpList.gdpList;
        gdplist.sort((o1,o2)-> o1.getName().compareToIgnoreCase(o2.getName()));
        return new ResponseEntity<>(gdpList, HttpStatus.OK);

    }
    // localhost:3000/economy/table - display a table list all countries sorted from most to least GDP
    @GetMapping(value = "/economy/table")
    public ModelAndView showDogTable(HttpServletRequest request) {
        logger.info(request.getRequestURI() + " accessed");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("gdp");
        mav.addObject("gdpList", gdpList.gdpList);
        return mav;
    }
    // localhost:3000/error
    @GetMapping(value="/error")
    public ResponseEntity<?> catchErrors(HttpServletRequest request){
        logger.info(request.getRequestURI() + "threw an error");

        throw new ResourceNotFoundException("Your requested resource doesn't exist, check for typo in URL");
    }
}
