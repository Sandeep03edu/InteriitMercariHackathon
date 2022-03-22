package com.techmeet.mercari.Model;

public class DoctorAppointment {
    private String hospitalName;
    private String doctorName;
    private String appointmentStatus;

    public DoctorAppointment(String hospitalName, String doctorName, String appointmentStatus) {
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
        this.appointmentStatus = appointmentStatus;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
