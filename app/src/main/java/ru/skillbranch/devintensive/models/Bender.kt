package ru.skillbranch.devintensive.models

class Bender(var status:Status = Bender.Status.NORMAL, var question:Question = Question.NAME) {
    fun askQuestion():String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.NAME.question
        Question.MATERIAL -> Question.NAME.question
        Question.BDAY -> Question.NAME.question
        Question.SERIAL -> Question.NAME.question
        Question.IDLE -> Question.NAME.question
    }
    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>>
    {
        return if(question.answers.contains(answer) && question.validate(answer))
        {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }
        else if(!question.validate(answer))
        {
            "${question.mistakes}\n${question.question}" to status.color
        }
        else if(status.equals(Status.CRITICAL))
        {
            status = status.nextStatus()
            question = Question.NAME
            "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
        }
        else if(question == Question.IDLE)
            question.question to status.color
        else{
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color
        }
    }

    enum class Status(val color : Triple<Int,Int,Int>){
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus():Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal +1]
            }
            else{
                values()[0]
            }
        }
    }
    enum class Question(val question:String,val answers:List<String>,val mistakes:String?){
        NAME("Как меня зовут?",listOf("Бендер","bender"),"Имя должно начинаться с заглавной буквы") {
            override fun nextQuestion(): Question  = PROFESSION
            override fun validate(ans: String): Boolean {
                return ans[0].isUpperCase()
            }
        },
        PROFESSION("Назови свою профессию?",listOf("сгибальщик","bender"), "Профессия должна начинаться со строчной буквы") {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(ans: String): Boolean {
                return ans[0].isLowerCase()
            }
        },
        MATERIAL("Из чего ты сделан?",listOf("металл", "дерево", "metal", "iron", "wood"),"Материал не должен содержать цифр") {
            override fun nextQuestion(): Question = BDAY
            override fun validate(ans: String): Boolean {
                return ans.any { it.isLetter()}
            }
        },
        BDAY("Когда меня собрали?",listOf("2993"), "Год моего рождения должен содержать только цифры") {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(ans: String): Boolean {
                return ans.any { it.isDigit() }
            }
        },
        SERIAL("Мой серийный номер?",listOf("2716057"),"Серийный номер содержит только цифры, и их 7") {
            override fun nextQuestion(): Question = IDLE
            override fun validate(ans: String): Boolean {
                return ans.any { it.isDigit() } && ans.length == 7
            }
        },
        IDLE("На этом все, вопросов больше нет",listOf(),null) {
            override fun nextQuestion(): Question = NAME
            override fun validate(ans: String): Boolean {
                return true
            }
        };

        abstract fun nextQuestion():Question
        abstract fun validate(ans:String):Boolean

        }
    }
