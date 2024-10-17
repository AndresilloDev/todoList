import React, { useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const FormContainer = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #ffffff; 
  border: 1px solid #000000; 
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.5);
  color: #000000;
  font-family: Consolas, 'Courier New', Courier, monospace; 
  width: 400px;
`;

const Input = styled.input`
  width: 100%;
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #000000;
  border-radius: 4px;
  background-color: #ffffff;
  color: #000000;
  
  &::placeholder {
    color: #bbb;
  }
`;

const Button = styled.button`
  padding: 10px;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #fff000; 
  }
`;

const TaskForm = ({ onClose, onTaskAdded }) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newTask = { name, description, date };
    await axios.post('http://localhost:8080/api/tasks', newTask);
    onTaskAdded(newTask);
    onClose();
    setName('');
    setDescription('');
    setDate('');
    window.location.reload();
  };

  return (
    <FormContainer>
      <h2>Añadir Tarea</h2>
      <form onSubmit={handleSubmit}>
        <Input
          type="text"
          placeholder="Nombre"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <Input
          type="text"
          placeholder="Descripción"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
        <Input
          type="date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
          required
        />
        <Button type="submit">Añadir</Button>
        <Button type="button" onClick={onClose} style={{ marginLeft: '10px' }}>
          Cancelar
        </Button>
      </form>
    </FormContainer>
  );
};

export default TaskForm;