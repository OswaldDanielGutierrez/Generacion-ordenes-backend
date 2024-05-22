package com.reto.generacion_ordenes_back.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DatosFormulario {

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String numeroDocumento;

    @NotEmpty
    private String telefono;

    @NotEmpty
    @Size(min = 1)
    private List<ProductoSeleccionado> productoSeleccionados;

    @Getter
    @Setter
    public static class ProductoSeleccionado{

        @NotEmpty
        private String nombre;

        @NotEmpty
        @Size(min = 1)
        private int cantidad;
    }

}
