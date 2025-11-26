import React, { useEffect, useState } from "react";
import api from "../api/api";
import { Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from "@mui/material";

export default function Compras(){
    const [compras, setCompras] = useState([]);
    const [loading, setLoading] = useState(true);

    const load = async () => {
        try {
            const res = await api.get("/compras/compras");
            setCompras(res.data || []);
        } catch (e) { console.error(e); }
        setLoading(false);
    };

    useEffect(() => { load(); }, []);

    return (
        <>
            <Typography variant="h5" sx={{ mb:2 }}>Compras</Typography>
            {loading ? <CircularProgress /> : (
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>PersonaID</TableCell>
                                <TableCell>Moto</TableCell>
                                <TableCell>Fecha</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {compras.map(c => (
                                <TableRow key={c.id}>
                                    <TableCell>{c.id}</TableCell>
                                    <TableCell>{c.personaId}</TableCell>
                                    <TableCell>{c.motoMatricula}</TableCell>
                                    <TableCell>{c.fecha || "-"}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </>
    );
}