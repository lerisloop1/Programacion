import React, { useEffect, useState } from "react";
import api from "../api/api";
import {
    Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Button, Box, CircularProgress
} from "@mui/material";
import { Link as RouterLink } from "react-router-dom";

export default function Personas(){
    const [personas, setPersonas] = useState([]);
    const [loading, setLoading] = useState(true);

    const load = async () => {
        try {
            const res = await api.get("/personas");
            setPersonas(res.data || []);
        } catch (e) { console.error(e); }
        setLoading(false);
    };

    useEffect(() => { load(); }, []);

    return (
        <>
            <Box sx={{display:"flex", justifyContent:"space-between", alignItems:"center", mb:2}}>
                <Typography variant="h5">Personas</Typography>
                <Button component={RouterLink} to="/registrar-persona" variant="contained">Nueva Persona</Button>
            </Box>

            {loading ? <CircularProgress /> : (
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>Nombre</TableCell>
                                <TableCell>Apellido</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {personas.map(p => (
                                <TableRow key={p.id}>
                                    <TableCell>{p.id}</TableCell>
                                    <TableCell>{p.nombre}</TableCell>
                                    <TableCell>{p.apellido}</TableCell>

                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </>
    );
}