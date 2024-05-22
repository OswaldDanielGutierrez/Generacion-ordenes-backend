package com.reto.generacion_ordenes_back.controller;

import com.reto.generacion_ordenes_back.entity.DatosFormulario;
import com.reto.generacion_ordenes_back.service.GenerarOrdenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/mekatico")
@CrossOrigin(value = "http://localhost:4200")
public class GenerarOrdenController {

    @Autowired
    GenerarOrdenService generarOrdenService;

    @PostMapping("/guardarOrden")
    public ResponseEntity<DatosFormulario> recibirFormulario(@RequestBody DatosFormulario datosFormulario) {
        return new ResponseEntity<>(generarOrdenService.recibirDatos(datosFormulario), HttpStatus.OK);
    }

    @GetMapping("/generarPdf")
    public void generarPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerkey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headerValue);

        this.generarOrdenService.export(response);
    }
}
