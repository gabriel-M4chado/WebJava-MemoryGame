async function getRandomCharacter() {
    try {
        const randomId = Math.floor(Math.random() * 671) + 1;

        const apiUrl = `https://rickandmortyapi.com/api/character/${randomId}`;
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error fetching character with ID ${randomId}`);
        }

        const data = await response.json();

        return {
            id: data.id,
            image: data.image,
        };
    } catch (error) {
        console.error('Error:', error.message);
        return null;
    }
}

