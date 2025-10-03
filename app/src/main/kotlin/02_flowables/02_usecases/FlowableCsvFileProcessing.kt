package org.rx.app.`02_flowables`.`02_usecases`

import io.reactivex.rxjava3.core.Flowable
import org.rx.app.models.CsvRecord
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun readCsvFile(path: String): Flowable<CsvRecord>{

    /*
        so in this method we will be creating a flowable observable which will read the csv file line
        by line and emit each line as a csv record object. We will start by using the flowable.using method
        where we will pass 3 arguments. The first argument will be a buffered reader object which will read the file. in its
        constructor we will pass the FileReader object which will take the file object in which we will pass the path in
        it.

        The second argument will be a lambda function which will take the buffered reader object as an argument
        in which we will access the readlines method. so we will first declare a variable called linenumber which
        will keep track of the line number being read, then we will access the readlines method which will read all the lines.
        We will use the generate method of type csvrecord, which is a lambda function which has the emitter object as an argument
        and then we will call the readerline method which will read the current line and pass it into a varible called line, which is now
        a string. we will check if the line is null. If it is null, then we will call the emitter on complete method otherwise we will increment the linenumber
        and then we will use the split method of the string class to split the line by commas. this will return an array of strings.

        we will then verify if the count of the array is 5 and we will create a csv record object by passing the values from the array to the constructor
        and then we will call the emitter on next method which will emit the csv record object. if there is an error, we will do that on the
        catch block where we will call the emitter on error method, actually tryonemitter method, passing the exception object
        , it can be a runtime exception or a throwable object.

        the third argument will be a lambda function which will take the buffered reader object as an argument
        and then we will call the close method to close the reader.
     */

    return Flowable.using(
        { BufferedReader(FileReader(File(path)))},
        {reader ->
            var lineNumber=0
            //skip the header line
            reader.readLine()
            Flowable.generate<CsvRecord> { emitter ->
                val line= reader.readLine()
                if(line==null)
                {
                    emitter.onComplete()
                }
                else{
                    lineNumber++
                    var values=line.split(",")
                    if(values.size==5){
                        val record= CsvRecord(
                            values[0].toInt(),
                            values[1],
                            values[2],
                            values[3],
                            values[4].toDouble()
                        )
                        emitter.onNext(record)
                    }
                    else{
                        emitter.onError(RuntimeException("Invalid csv format at line number ${lineNumber}"))
                    }
                }
            }
        },
        {reader -> reader.close()}
    )

}

fun main(){
    /*
        here in the main method we will call the readCsvFile method passing the csv file path
        and in this we will subscribe to the flowable observable and access the onNext, onError and onComplete methods.
        in the onNext method we will print the csv record object, in the onError method we will print the error message
        and in the onComplete method we will print a message indicating that the file has been read completely.
     */
    val csvFilePath="app/src/main/kotlin/15_utils/sample.csv"
    readCsvFile(csvFilePath).subscribe(
        { record -> println("Read CSV Record: ${record.department}")},
        { error -> println("Error occurred while reading CSV file: $error")},
        { println("Completed reading the CSV file")}
    )

}