/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.controller;

import java.util.Date;
import mn.ineg.model.MFlightSchedule;
import mn.ineg.service.FlightSchedulerCrudRepository;
import mn.ineg.service.FlightSchedulerRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author developer
 */
@Controller
@RequestMapping("/flight")
public class FlightScheduleController {

    @Autowired
    private FlightSchedulerRestRepository flightRestRepository;

    @Autowired
    private FlightSchedulerCrudRepository flightCrudRepository;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String listFlightSchedule(Model model) {
        model.addAttribute("schedules", flightRestRepository.findAll());
        return "news/flightSchedule";
    }

    /**
     * Add new Flight Schedule
     *
     * @return
     */
    @RequestMapping("/new")
    public ModelAndView addFlightSchedule() {
        ModelAndView view = new ModelAndView("news/flightScheduleAdd");
        view.addObject("schedule", new MFlightSchedule());
        return view;
    }
    /**
     * Edit the existing Flight Schedule
     * @param id
     * @return 
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editFlightSchedule(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("news/flightScheduleEdit");
        view.addObject("schedule", flightRestRepository.findOne(id));
        return view;
    }
    
    /**
     * Delete the existing Flight Schedule
     * @param id
     * @return 
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFlightSchedule(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("redirect:/flight/list");
        flightCrudRepository.delete(id);
        return view;
    }

    /**
     * Save new schedule object
     * @param action
     * @param departure
     * @param destination
     * @param trip_number
     * @param flight_company
     * @param time_departure
     * @param time_destination
     * @param date_begin
     * @param date_end
     * @return
     */
    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public ModelAndView saveFlightSchedule(
            @RequestParam("action") String action,
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("tripNumber") String trip_number,
            @RequestParam("flightCompany") String flight_company,
            @RequestParam("timeDeparture") String time_departure,
            @RequestParam("timeDestination") String time_destination,
            @RequestParam("dateBegin") String date_begin,
            @RequestParam("dateEnd") String date_end
    ) throws ParseException {

        ModelAndView view = new ModelAndView("redirect:/flight/list");
        if (action.equals("save")) {
            MFlightSchedule schedule = new MFlightSchedule();
            DateFormat sdf = new SimpleDateFormat("HH:mm");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
            schedule.setDeparture(departure);
            schedule.setDestination(destination);
            schedule.setTripNumber(trip_number);
            schedule.setFlightCompany(flight_company);
            schedule.setTimeDeparture(new Time(sdf.parse(time_departure).getTime()));
            schedule.setTimeDestination(new Time(sdf.parse(time_destination).getTime()));
            schedule.setDateBegin(new Date(dateFormatter.parse(date_begin).getTime()));
            schedule.setDateEnd(new Date(dateFormatter.parse(date_end).getTime()));
            
            System.out.println("Departure " + departure);
            System.out.println("destination " + destination);
            System.out.println("trip number " + trip_number);
            System.out.println("company " + flight_company);
            System.out.println("time departure " + new Time(sdf.parse(time_departure).getTime()));
            System.out.println("time destination " + new Time(sdf.parse(time_destination).getTime()));
            System.out.println("date begin " + new Date(dateFormatter.parse(date_begin).getTime()));
            System.out.println("date end " + new Date(dateFormatter.parse(date_begin).getTime()));
            
            flightCrudRepository.save(schedule);
            System.out.println("Flight Scheduler add saved");
        }

        if (action.equals("cancel")) {
            System.out.println("Flight Scheduler add canceled");
        }
        return view;
    }
    /**
     * 
     * @param action
     * @param departure
     * @param destination
     * @param trip_number
     * @param flight_company
     * @param time_departure
     * @param time_destination
     * @param date_begin
     * @param date_end
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public ModelAndView editFlightSchedule(
            @RequestParam("action") String action,
            @RequestParam("id") Integer id,
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("tripNumber") String trip_number,
            @RequestParam("flightCompany") String flight_company,
            @RequestParam("timeDeparture") String time_departure,
            @RequestParam("timeDestination") String time_destination,
            @RequestParam("dateBegin") String date_begin,
            @RequestParam("dateEnd") String date_end
    ) throws ParseException {

        ModelAndView view = new ModelAndView("redirect:/flight/list");
        if (action.equals("save")) {
            MFlightSchedule schedule = flightRestRepository.findOne(id);
            DateFormat sdf = new SimpleDateFormat("HH:mm");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
            schedule.setDeparture(departure);
            schedule.setDestination(destination);
            schedule.setTripNumber(trip_number);
            schedule.setFlightCompany(flight_company);
            schedule.setTimeDeparture(new Time(sdf.parse(time_departure).getTime()));
            schedule.setTimeDestination(new Time(sdf.parse(time_destination).getTime()));
            schedule.setDateBegin(new Date(dateFormatter.parse(date_begin).getTime()));
            schedule.setDateEnd(new Date(dateFormatter.parse(date_end).getTime()));
            
            System.out.println("Departure " + departure);
            System.out.println("destination " + destination);
            System.out.println("trip number " + trip_number);
            System.out.println("company " + flight_company);
            System.out.println("time departure " + new Time(sdf.parse(time_departure).getTime()));
            System.out.println("time destination " + new Time(sdf.parse(time_destination).getTime()));
            System.out.println("date begin " + new Date(dateFormatter.parse(date_begin).getTime()));
            System.out.println("date end " + new Date(dateFormatter.parse(date_begin).getTime()));
            flightCrudRepository.save(schedule);
            System.out.println("Flight Scheduler add saved");
        }

        if (action.equals("cancel")) {
            System.out.println("Flight Scheduler add canceled");
        }
        return view;
    }

    /**
     * Give Date from String
     * @param dateString
     * @return 
     */
    private Date getDate(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate = new Date();
        try {
            startDate = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

}
