package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Opcional;
import org.modelmapper.ModelMapper;

public class OpcionalDTO {
    private Long id;

    private String descricao;
    private Boolean arCondicionado;
    private Boolean direcaoHidraulica;
    private Boolean vidroEletrico;
    private Boolean cameraRe;
    private Boolean sensor;
    private Boolean completo;

    public static OpcionalDTO create(Opcional opcional) {
        ModelMapper modelMapper = new ModelMapper();
        OpcionalDTO dto = modelMapper.map(opcional, OpcionalDTO.class);
        return dto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(Boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public Boolean getDirecaoHidraulica() {
        return direcaoHidraulica;
    }

    public void setDirecaoHidraulica(Boolean direcaoHidraulica) {
        this.direcaoHidraulica = direcaoHidraulica;
    }

    public Boolean getVidroEletrico() {
        return vidroEletrico;
    }

    public void setVidroEletrico(Boolean vidroEletrico) {
        this.vidroEletrico = vidroEletrico;
    }

    public Boolean getCameraRe() {
        return cameraRe;
    }

    public void setCameraRe(Boolean cameraRe) {
        this.cameraRe = cameraRe;
    }

    public Boolean getSensor() {
        return sensor;
    }

    public void setSensor(Boolean sensor) {
        this.sensor = sensor;
    }

    public Boolean getCompleto() {
        return completo;
    }

    public void setCompleto(Boolean completo) {
        this.completo = completo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
