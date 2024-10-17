// src/App.js
import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import axios from 'axios';

const AppContainer = styled.div`
  background-color: #1a1a1a;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #000000;
  background-color: #ffffff;
  font-family: Consolas, 'Courier New', Courier, monospace;
`;

const FloatingButton1 = styled.button`
  position: fixed;
  bottom: 30px;
  right: 30px;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);

  &:hover {
    background-color: #fff000;
  }
`;

const FloatingButton2 = styled.button`
  position: fixed;
  bottom: 30px;
  right: 100px;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);

  &:hover {
    background-color: #fff000;
  }
`;

const FloatingButton3 = styled.button`
  position: fixed;
  bottom: 30px;
  right: 170px;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 50%;
  width: 60px;
  height: 60px;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);

  &:hover {
    background-color: #fff000;
  }
`;

const App = () => {
  const [isFormVisible, setIsFormVisible] = useState(false);
  const [tasks, setTasks] = useState([]);

  const toggleFormVisibility = () => {
    setIsFormVisible((prev) => !prev);
  };

  const cleanTaskList = async () => {
    try {
      await axios.delete('http://localhost:8080/api/tasks/clear');
      fetchTasks();
      window.location.reload();
    } catch (error) {
      console.error('Error al limpiar las tareas:', error);
    }
  };

  const pendingTaskList = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/tasks/pending');
      setTasks(response.data);
    } catch (error) {
      console.error('Error al obtener las tareas pendientes:', error);
    }
  };

  const fetchTasks = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/tasks/all');
      setTasks(response.data);
    } catch (error) {
      console.error('Error al obtener las tareas:', error);
    }
  };

  const downloadTasksAsTxt = () => {
    // Convertir la lista de tareas a un formato de texto con todos los detalles
    const taskText = tasks.map(task => 
      `\nNombre: ${task.name}\nDescripción: ${task.description}\nEstado: ${task.isPending ? 'Pendiente' : 'Completada'}\nFecha de Creación: ${new Date(task.creationDate).toLocaleString()}\n`
    ).join('\n-----------------------\n');
    
    // Crear un blob con el contenido de texto
    const blob = new Blob([taskText], { type: 'text/plain' });
    
    // Crear un enlace para descargar el archivo
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'tareas.txt';
    link.click();
    
    // Liberar el objeto URL
    URL.revokeObjectURL(link.href);
  };
  

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleTaskAdded = (newTask) => {
    setTasks((prevTasks) => [...prevTasks, newTask]);
  };

  return (
    <AppContainer>
      {isFormVisible && (
        <TaskForm
          onClose={toggleFormVisibility}
          onTaskAdded={handleTaskAdded}
        />
      )}
      <TaskList tasks={tasks} />
      <FloatingButton1 onClick={toggleFormVisibility}>📒</FloatingButton1>
      <FloatingButton2 onClick={cleanTaskList}>🗑️</FloatingButton2>
      <FloatingButton3 onClick={downloadTasksAsTxt}>📄</FloatingButton3>
    </AppContainer>
  );
};

export default App;