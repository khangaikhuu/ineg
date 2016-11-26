/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.ineg.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "m_flight_schedule")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "MFlightSchedule.findAll", query = "SELECT m FROM MFlightSchedule m"),
//    @NamedQuery(name = "MFlightSchedule.findById", query = "SELECT m FROM MFlightSchedule m WHERE m.id = :id"),
//    @NamedQuery(name = "MFlightSchedule.findByDeparture", query = "SELECT m FROM MFlightSchedule m WHERE m.departure = :departure"),
//    @NamedQuery(name = "MFlightSchedule.findByDestination", query = "SELECT m FROM MFlightSchedule m WHERE m.destination = :destination"),
//    @NamedQuery(name = "MFlightSchedule.findByTripNumber", query = "SELECT m FROM MFlightSchedule m WHERE m.tripNumber = :tripNumber"),
//    @NamedQuery(name = "MFlightSchedule.findByTimeDeparture", query = "SELECT m FROM MFlightSchedule m WHERE m.timeDeparture = :timeDeparture"),
//    @NamedQuery(name = "MFlightSchedule.findByTimeDestination", query = "SELECT m FROM MFlightSchedule m WHERE m.timeDestination = :timeDestination"),
//    @NamedQuery(name = "MFlightSchedule.findByFlightCompany", query = "SELECT m FROM MFlightSchedule m WHERE m.flightCompany = :flightCompany"),
//    @NamedQuery(name = "MFlightSchedule.findByDateBegin", query = "SELECT m FROM MFlightSchedule m WHERE m.dateBegin = :dateBegin"),
//    @NamedQuery(name = "MFlightSchedule.findByDateEnd", query = "SELECT m FROM MFlightSchedule m WHERE m.dateEnd = :dateEnd")})
public class MFlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "departure")
    private String departure;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "destination")
    private String destination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "trip_number")
    private String tripNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_departure")
    @Temporal(TemporalType.TIME)
    private Date timeDeparture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_destination")
    @Temporal(TemporalType.TIME)
    private Date timeDestination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "flight_company")
    private String flightCompany;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_begin")
    @Temporal(TemporalType.DATE)
    private Date dateBegin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_end")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    public MFlightSchedule() {
    }

    public MFlightSchedule(Integer id) {
        this.id = id;
    }

    public MFlightSchedule(Integer id, String departure, String destination, String tripNumber, Date timeDeparture, Date timeDestination, String flightCompany, Date dateBegin, Date dateEnd) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.tripNumber = tripNumber;
        this.timeDeparture = timeDeparture;
        this.timeDestination = timeDestination;
        this.flightCompany = flightCompany;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public Date getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Date getTimeDestination() {
        return timeDestination;
    }

    public void setTimeDestination(Date timeDestination) {
        this.timeDestination = timeDestination;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public void setFlightCompany(String flightCompany) {
        this.flightCompany = flightCompany;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MFlightSchedule)) {
            return false;
        }
        MFlightSchedule other = (MFlightSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mn.ineg.model.MFlightSchedule[ id=" + id + " ]";
    }
    
}
