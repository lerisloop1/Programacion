import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import api from "../api/api";
import { useNavigate } from "react-router-dom";
import {
    Box,
    Button,
    TextField,
    MenuItem,
    Typography
} from "@mui/material";

export default function RegistrarCompra() {
    const location = useLocation();
    const motoSeleccionada = location.state?.moto || null;

    const [personas, setPersonas] = useState([]);
    const [motos, setMotos] = useState([]);
    const [form, setForm] = useState({
        id: "",
        motoMatricula: "",
        precio: ""
    });

    // 🔥 Si viene una moto seleccionada desde la pantalla anterior, setearla automáticamente
    useEffect(() => {
        if (motoSeleccionada) {
            setForm((prev) => ({
                ...prev,
                motoMatricula: motoSeleccionada.matricula,
                precio: motoSeleccionada.precio
            }));
        }
    }, [motoSeleccionada]);


    const cargarDatos = async () => {
        try {
            const resPersonas = await api.get("/personas");
            setPersonas(resPersonas.data);

            const resMotos = await api.get("/motos");
            setMotos(resMotos.data);
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        cargarDatos();
    }, []);

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };
    const registrar = async (e) => {
        e.preventDefault();

        try {
            await api.post("/compras/comprar", form);
            alert("Compra registrada exitosamente!");
        } catch (err) {
            console.error(err);
            alert("Error al registrar la compra");
        }
    };

    return (
        <Box sx={{ maxWidth: 500, mx: "auto", mt: 4 }}>
            <Typography variant="h4" sx={{ mb: 3 }}>
                Registrar Compra
            </Typography>

            <form onSubmit={registrar}>

                <TextField
                    label="Persona"
                    name="personaId"
                    select
                    fullWidth
                    value={form.personaId}
                    onChange={handleChange}
                    required
                    sx={{ mb: 2 }}
                >
                    {personas.map((p) => (
                        <MenuItem key={p.id} value={p.id}>
                            {p.id} - {p.nombre}
                        </MenuItem>
                    ))}
                </TextField>


                <TextField
                    label="Moto"
                    name="matricula"
                    select
                    fullWidth
                    value={form.motoMatricula}
                    onChange={handleChange}
                    required
                    disabled={!!motoSeleccionada}
                    sx={{ mb: 2 }}
                >
                    {motos
                        .filter((m) => m.disponibilidad)
                        .map((m) => (
                            <MenuItem key={m.matricula} value={m.matricula}>
                                {m.marca} - {m.modelo} ({m.matricula})
                            </MenuItem>
                        ))}
                </TextField>


                <TextField
                    label="Precio"
                    name="precio"
                    fullWidth
                    value={form.precio}
                    onChange={handleChange}
                    required
                    disabled
                    sx={{ mb: 3 }}
                />

                <Button type="submit" variant="contained" color="primary" fullWidth >
                    Registrar Compra
                </Button>
            </form>
        </Box>
    );
}
