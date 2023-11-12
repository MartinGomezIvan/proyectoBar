package domain.Datos;

public class Cita {
    private String titularCita;
    private String fechaCita;
    private String especialidadesCitas;
    private String medicoCita;
    private String motivoCita;

    public Cita(String titularCita, String fechaCita, String especialidadesCitas, String medicoCita, String motivoCita) {
        this.titularCita = titularCita;
        this.fechaCita = fechaCita;
        this.especialidadesCitas = especialidadesCitas;
        this.medicoCita = medicoCita;
        this.motivoCita = motivoCita;
    }
    public Cita(){

    }

    public String getTitularCita() {
        return titularCita;
    }

    public void setTitularCita(String titularCita) {
        this.titularCita = titularCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEspecialidadesCitas() {
        return especialidadesCitas;
    }

    public void setEspecialidadesCitas(String especialidadesCitas) {
        this.especialidadesCitas = especialidadesCitas;
    }

    public String getMedicoCita() {
        return medicoCita;
    }

    public void setMedicoCita(String medicoCita) {
        this.medicoCita = medicoCita;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    @Override
    public String toString() {
        return "titular='" + titularCita + '\'' +
                ", fecha='" + fechaCita + '\'' +
                ", especialidad='" + especialidadesCitas + '\'' +
                ", medico='" + medicoCita + '\'' +
                ", motivo='" + motivoCita + '\'' +
                '}';
    }
}
