package com.example.myapplication.state_movie_module.models

data class StateMovieModel(
    val name: String = "No Name",
    val des: String = "No Description",
    val image: String = "No Image",
)

val stateMovieList1: MutableList<StateMovieModel> = mutableListOf(
    StateMovieModel("Spider-Man: Into the Spider-Verse", "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.", "https://www.themoviedb.org/t/p/original/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"),
    StateMovieModel("The Dark Knight", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.", "https://www.themoviedb.org/t/p/original/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg"),
    StateMovieModel("Avengers: Endgame", "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.", "https://www.themoviedb.org/t/p/original/q6725aR8Zs4IwGMXzZT8aC8lh41.jpg"),
    StateMovieModel("Iron Man", "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.", "https://www.themoviedb.org/t/p/original/78lPtwv72eTNqFW9COBYI0dWDJa.jpg"),
    StateMovieModel("Wonder Woman", "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.", "https://www.themoviedb.org/t/p/original/6iUNJZymJBMXXriQyFZfLAKnjO6.jpg"),
    StateMovieModel("Black Panther", "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past.", "https://www.themoviedb.org/t/p/original/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"),
    StateMovieModel("Captain America: The Winter Soldier", "As Steve Rogers struggles to embrace his role in the modern world, he teams up with a fellow Avenger and S.H.I.E.L.D agent, Black Widow, to battle a new threat from history: an assassin known as the Winter Soldier.", "https://www.themoviedb.org/t/p/original/qbUmbVOVVjCGQbSKpsFV5tmZjX6.jpg"),
    StateMovieModel("Guardians of the Galaxy", "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.", "https://www.themoviedb.org/t/p/original/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg"),
    StateMovieModel("Deadpool", "A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.", "https://www.themoviedb.org/t/p/original/inVq3FRqcYIRl2la8iZikYYxFNR.jpg"),
    StateMovieModel("The Incredibles", "A family of undercover superheroes, while trying to live the quiet suburban life, are forced into action to save the world.", "https://www.themoviedb.org/t/p/original/hhjMv8IXUxcApAaV8eDdpdoQxj.jpg"),
    StateMovieModel("Thor: Ragnarok", "Imprisoned on the planet Sakaar, Thor must race against time to return to Asgard and stop Ragnarök, the destruction of his world, at the hands of the powerful and ruthless villain Hela.", "https://www.themoviedb.org/t/p/original/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg"),
    StateMovieModel("X-Men: Days of Future Past", "The X-Men send Wolverine to the past in a desperate effort to change history and prevent an event that results in doom for both humans and mutants.", "https://www.themoviedb.org/t/p/original/g51t83hrgPf3Y6sHO8nWyFVspgV.jpg"),
    StateMovieModel("Shazam!", "A newly fostered young boy in search of his mother instead finds unexpected super powers and soon gains a powerful enemy.", "https://www.themoviedb.org/t/p/original/xnopI5Xtky18MPhK40cZAGAOVeV.jpg"),
    StateMovieModel("Doctor Strange", "While on a journey of physical and spiritual healing, a brilliant neurosurgeon is drawn into the world of the mystic arts.", "https://www.themoviedb.org/t/p/original/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg"),
    StateMovieModel("Ant-Man", "Armed with a super-suit with the astonishing ability to shrink in scale but increase in strength, cat burglar Scott Lang must embrace his inner hero and help his mentor, Dr. Hank Pym, plan and pull off a heist that will save the world.", "https://www.themoviedb.org/t/p/original/D6e8RJf2qUstnfkTslTXNTUAlT.jpg"),
    StateMovieModel("Aquaman", "Arthur Curry, the human-born heir to the underwater kingdom of Atlantis, goes on a quest to prevent a war between the worlds of ocean and land.", "https://www.themoviedb.org/t/p/original/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg"),
    StateMovieModel("The Avengers", "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", "https://www.themoviedb.org/t/p/original/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg"),
    StateMovieModel("Captain Marvel", "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.", "https://www.themoviedb.org/t/p/original/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg"),
    StateMovieModel("Iron Man 3", "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.", "https://www.themoviedb.org/t/p/original/7XiGqZE8meUv7L4720lU6uvH2bL.jpg"),
    StateMovieModel("Batman Begins", "After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.", "https://www.themoviedb.org/t/p/original/hBd9boVkvW1k8W8thnV5UdLhBVY.jpg"),
    StateMovieModel("Man of Steel", "An alien child is evacuated from his dying world and sent to Earth to live among humans. His peace is threatened, when survivors of his home planet invade Earth.", "https://www.themoviedb.org/t/p/original/7rIPjn5TUK04O25ZkMyHrGNPgLx.jpg")
)

