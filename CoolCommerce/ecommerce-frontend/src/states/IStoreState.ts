import { container, injectable } from 'inversify-props'
import { reactive } from '@vue/composition-api'
import Category from '@/domain/Category'
import {Cart} from '@/states/Cart'
import {Wishlist} from '@/states/Wishlist'

export interface IStoreState {

    categories: Category[]

    wishlist: Wishlist

    cart: Cart
}

@injectable()
export class StoreState implements IStoreState{

    categories: Category[] = []

    wishlist: Wishlist = reactive<Wishlist>(new Wishlist()) as Wishlist

    cart: Cart = reactive<Cart>(new Cart()) as Cart
}

container.addSingleton<IStoreState>(StoreState).onActivation((context, storeState) => {
    // This line is what does the magic and makes the class a "state" object that vue can react too
    return reactive<IStoreState>(storeState) as IStoreState
})

