import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import TaskCard from './TaskCard';

const ListContainer = styled.div`
  padding: 20px;
  margin: 20px;
  background-color: #ffffff;
  border-radius: 5px;
  color: #000000;
  font-family: Consolas, 'Courier New', Courier, monospace;
`;

const Input = styled.input`
  padding: 10px;
  margin-bottom: 10px;
  width: 100%;
  background-color: #ffffff;
  color: #000000;
  border: 1px solid #000000;
  border-radius: 4px;
  width: 400px;

  &::placeholder {
    color: #000000;
  }
`;

const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-bottom: 10px;
`;

const ButtonSwitch = styled.button`
  padding: 8px 12px;
  background-color: #ffffff;
  border: 1px solid #000000;
  cursor: pointer;
  border-radius: 4px;
  width: 200px;
  text-align: center;
`;

const TaskList = () => {
  const [tasks, setTasks] = useState([]);
  const [filter, setFilter] = useState('');
  const [showPending, setShowPending] = useState(false);
  const [pendingCount, setPendingCount] = useState(0);

  const fetchTasks = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/tasks/all');
      setTasks(response.data);
      updatePendingCount(response.data);
    } catch (error) {
      console.error('Error al obtener las tareas:', error);
    }
  };

  const updatePendingCount = (tasks) => {
    const pendingTasks = tasks.filter((task) => task.isPending);
    setPendingCount(pendingTasks.length);
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleTaskAdded = async (newTask) => {
    setTasks((prev) => [...prev, newTask]);
    updatePendingCount([...tasks, newTask]);
  };

  const handleTaskRemoved = (taskName) => {
    const updatedTasks = tasks.filter((task) => task.name !== taskName);
    setTasks(updatedTasks);
    updatePendingCount(updatedTasks);
  };

  const handleTaskToggled = (taskName) => {
    const updatedTasks = tasks.map((task) =>
      task.name === taskName ? { ...task, isPending: !task.isPending } : task
    );
    setTasks(updatedTasks);
    updatePendingCount(updatedTasks);
  };

  const filteredTasks = tasks
    .filter((task) =>
      task.name.toLowerCase().includes(filter.toLowerCase())
    )
    .filter((task) => (showPending ? task.isPending : true));

  const toggleShowPending = () => {
    setShowPending((prev) => !prev);
  };

  return (
    <ListContainer>
      <Header>
        <div>Tareas pendientes: {pendingCount}</div>
        <ButtonSwitch onClick={toggleShowPending}>
          {showPending ? 'Viendo: Pendientes' : 'Viendo: Todas'}
        </ButtonSwitch>
      </Header>
      <Input
        type="text"
        placeholder="Buscar por nombre..."
        value={filter}
        onChange={(e) => setFilter(e.target.value)}
      />
      {filteredTasks.map((task) => (
        <TaskCard
          key={task.name}
          task={task}
          onTaskRemoved={handleTaskRemoved}
          onTaskToggled={handleTaskToggled}
          onTaskAdded={handleTaskAdded}
        />
      ))}
    </ListContainer>
  );
};

export default TaskList;