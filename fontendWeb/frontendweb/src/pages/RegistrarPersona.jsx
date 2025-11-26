import React, { useState } from "react";
import api from "../api/api";
import { Container, Typography, TextField, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function RegistrarPersona(){
    const [id, setId] = useState("");
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const navigate = useNavigate();

    const handle = async () => {
        if(!nombre) { alert("Nombre requerido"); return; }
        try {
            await api.post("/personas", {
                id: id,
                nombre: nombre,
                apellido: apellido
                });
            alert("Persona creada");
            navigate("/personas");
        } catch (error) {
            alert(error.response.data);}
    };

    return (
        <Container>
            <Typography variant="h5" sx={{ mb:2 }}>Registrar Persona</Typography>
            <TextField fullWidth label="Numero documento" value={id} onChange={e=>setId(e.target.value)} sx={{ mb:2 }} />
            <TextField fullWidth label="Nombre" value={nombre} onChange={e=>setNombre(e.target.value)} sx={{ mb:2 }} />
            <TextField fullWidth label="Apellido" value={apellido} onChange={e=>setApellido(e.target.value)} sx={{ mb:2 }} />
            <Box><Button variant="contained" onClick={handle}>Crear</Button></Box>
        </Container>
    );
}
