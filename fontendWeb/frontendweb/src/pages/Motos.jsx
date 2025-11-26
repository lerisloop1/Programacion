import React, { useEffect, useState } from "react";
import api from "../api/api";
import {
    Typography,
    Card,
    CardContent,
    CardMedia,
    Grid,
    CircularProgress,
    Box,
    Modal,
    Button
} from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function Motos() {
    const [motos, setMotos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [seleccionada, setSeleccionada] = useState(null);
    const [openModal, setOpenModal] = useState(false);

    const navigate = useNavigate();

    const load = async () => {
        try {
            const res = await api.get("/motos");
            setMotos(res.data || []);
        } catch (e) {
            console.error(e);
        }
        setLoading(false);
    };

    useEffect(() => {
        load();
    }, []);

    const abrirPopupCompra = (moto) => {
        if (moto.disponibilidad) {
            setSeleccionada(moto);
            setOpenModal(true);
        }
    };

    const confirmarCompra = () => {
        setOpenModal(false);
        navigate("/registrar-compra", {
            state: { moto: seleccionada }
        });
    };

    const cerrarModal = () => {
        setOpenModal(false);
        setSeleccionada(null);
    };

    if (loading)
        return (
            <Box sx={{ display: "flex", justifyContent: "center", py: 5 }}>
                <CircularProgress />
            </Box>
        );

    if (motos.length === 0)
        return <Typography>No hay motos registradas</Typography>;

    const motosPorMarca = motos.reduce((acc, moto) => {
        acc[moto.marca] = acc[moto.marca] || [];
        acc[moto.marca].push(moto);
        return acc;
    }, {});

    return (
        <>
            <Typography variant="h4" sx={{ mb: 3 }}>
                Catálogo de Motos
            </Typography>

            {Object.keys(motosPorMarca).map((marca) => (
                <Box key={marca} sx={{ mb: 5 }}>
                    <Typography variant="h5" sx={{ mb: 2 }}>
                        {marca}
                    </Typography>

                    <Grid container spacing={3}>
                        {motosPorMarca[marca].map((moto) => (
                            <Grid item xs={12} sm={6} md={3} key={moto.matricula}>
                                <Card
                                    onClick={() => abrirPopupCompra(moto)}
                                    sx={{
                                        borderRadius: 3,
                                        boxShadow: 3,
                                        overflow: "hidden",
                                        cursor: moto.disponibilidad
                                            ? "pointer"
                                            : "not-allowed",
                                        opacity: moto.disponibilidad ? 1 : 0.5,
                                        transition: "0.3s",
                                        "&:hover": {
                                            transform: moto.disponibilidad
                                                ? "scale(1.03)"
                                                : "none"
                                        }
                                    }}
                                >
                                    <CardMedia
                                        component="img"
                                        height="160"
                                        image={
                                            moto.foto ||
                                            "https://pngimg.com/uploads/motorcycle/motorcycle_PNG3157.png"
                                        }
                                        alt={moto.modelo}
                                    />

                                    <CardContent>
                                        <Typography variant="h6">{moto.modelo}</Typography>

                                        <Typography variant="body2" color="text.secondary">
                                            Matrícula: {moto.matricula}
                                        </Typography>

                                        <Typography variant="body2" color="text.secondary">
                                            Precio: ${moto.precio}
                                        </Typography>

                                        <Typography
                                            variant="body2"
                                            sx={{
                                                color: moto.disponibilidad ? "green" : "red",
                                                fontWeight: "bold",
                                                mt: 1
                                            }}
                                        >
                                            {moto.disponibilidad
                                                ? "Disponible"
                                                : "No disponible"}
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </Box>
            ))}

            {/* --- MODAL DE CONFIRMACIÓN --- */}
            <Modal open={openModal} onClose={cerrarModal}>
                <Box
                    sx={{
                        position: "absolute",
                        top: "50%",
                        left: "50%",
                        transform: "translate(-50%, -50%)",
                        width: 380,
                        color:"black",
                        bgcolor: "white",
                        boxShadow: 24,
                        p: 4,
                        borderRadius: 3,
                        textAlign: "center"
                    }}
                >
                    <Typography variant="h6" sx={{ mb: 2 }}>
                        ¿Desea comprar esta moto?
                    </Typography>

                    {seleccionada && (
                        <>
                            <Typography><strong>Modelo:</strong> {seleccionada.modelo}</Typography>
                            <Typography><strong>Marca:</strong> {seleccionada.marca}</Typography>
                            <Typography><strong>Precio:</strong> ${seleccionada.precio}</Typography>
                        </>
                    )}

                    <Box sx={{ mt: 3, display: "flex", gap: 2, justifyContent: "center" }}>
                        <Button variant="contained" color="success" onClick={confirmarCompra}>
                            Comprar
                        </Button>
                        <Button variant="outlined" onClick={cerrarModal}>
                            Cancelar
                        </Button>
                    </Box>
                </Box>
            </Modal>
        </>
    );
}
