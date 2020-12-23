import { useEffect, useState } from 'react'

function PrettyPrint (props) {
  return <pre>{JSON.stringify(props.jsonObj, null, 2)}</pre>
}


function App() {

  const [reminders, setReminders] = useState([])

  useEffect(async () => {
    const response = await fetch('http://localhost:8080/reminders')
    const reminders = await response.json();
    console.log(reminders)
    setReminders(reminders)
  }, [])

  return (
    <div>
      <h1>Hello</h1>
      <ul>
        {reminders.map(reminder => <li>{reminder.id} > {reminder.birthdayDate} > {reminder.email}</li>)}
      </ul>
      <PrettyPrint jsonObj={reminders}/>
    </div>
  );
}

export default App;
