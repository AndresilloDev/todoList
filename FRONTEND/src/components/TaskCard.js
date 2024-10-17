// src/components/TaskCard.js
import React, { useState } from 'react';
import styled, { keyframes } from 'styled-components';
import axios from 'axios';

const slideOut = keyframes`
  0% {
    transform: translateX(0);
    opacity: 1;
  }
  100% {
    transform: translateX(-100%);
    opacity: 0;
  }
`;

const Card = styled.div`
  padding: 20px;
  margin-bottom: 10px;
  border: 1px dashed #000000;
  border-radius: 5px;
  transition: transform 0.3s ease;
  display: flex;
  flex-direction: column;
  color: #000000;
  font-family: 'Courier New', Courier, monospace;
  animation: ${({ isRemoving }) => isRemoving ? slideOut : 'none'} 0.3s forwards;
  width: 400px;


`;

const TaskName = styled.span`
  text-decoration: ${({ isPending }) => (isPending ? 'none' : 'line-through')};
`;

const Description = styled.p`
  text-decoration: ${({ isPending }) => (isPending ? 'none' : 'line-through')};
`;

const Button = styled.button`
  margin-top: 10px;
  padding: 8px;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: ${({ children }) => children === 'Eliminar' ? '#f00000' : '#fff000'};
  }
`;

const TaskCard = ({ task, onTaskRemoved, onTaskToggled }) => {
  const [isRemoving, setIsRemoving] = useState(false);

  const handleRemove = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/tasks/${task.name}`);
      setIsRemoving(true);
      setTimeout(() => {
        onTaskRemoved(task.name);
      }, 500);
    } catch (error) {
      console.error('Error al eliminar la tarea:', error);
    }
  };

  const handleToggle = async () => {
    try {
      await axios.post(`http://localhost:8080/api/tasks/${task.name}/toggle`, {
        ...task,
        isPending: !task.isPending,
      });
      onTaskToggled(task.name);
    } catch (error) {
      console.error('Error al marcar la tarea:', error)
    }
  };

  return (
    <Card isRemoving={isRemoving}>
      <TaskName isPending={task.isPending}>{task.name}</TaskName>
      <Description isPending={task.isPending}>{task.description}</Description>

      <p>{task.date}</p>
      <Button onClick={handleToggle}>
        {task.isPending ? 'Marcar como completada' : 'Marcar como pendiente'}
      </Button>
      <Button onClick={handleRemove}>
        Eliminar
      </Button>
    </Card>
  );
};

export default TaskCard;